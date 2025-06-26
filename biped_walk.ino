//====================SILHOUETTE (README)====================
/* This repository contains code for Silhouette, an industrial-grade bipedal robot 
   developed in collaboration with UCI's Biorobotics Laboratory. 
   This system tightly integrates precise hardware with advanced software to explore 
   on-the-fly gait analysis, balance correction, and adaptive motion profiling 
   using motor control and sensor feedback. */

//====================CONSTANTS====================
const int EXECUTING_LED_BLINK_FREQUENCY = 50; //Milliseconds 

const int ENA_LEFT = 4; //Green
const int IN1 = 5; //Blue
const int IN2 = 6; //Purple

const int ENA_RIGHT = 8; //Green
const int IN3 = 9; //Blue
const int IN4 = 10; //Purple

const int SILHOUETTE_SPEED = 255; //PWM signal ranging from 0-255 inclusive, 255 being max speed

//====================SILHOUETTE SUPER STATES====================
enum SilhouetteState {
  FORWARD,
  BACKWARD,
  BRAKE,
  COAST
};

SilhouetteState SILHOUETTE_STATE = BACKWARD; //Current SILHOUETTE State

//====================SETUP LOOP====================
void setup() {
  //Initialization of onboard LED and arduino ports for use in the PERIODIC LOOP
  pinMode(LED_BUILTIN, OUTPUT);
  pinMode(ENA_LEFT, OUTPUT);
  pinMode(IN1, OUTPUT);
  pinMode(IN2, OUTPUT);
  pinMode(ENA_RIGHT, OUTPUT);
  pinMode(IN3, OUTPUT);
  pinMode(IN4, OUTPUT);
}

//====================PERIODIC LOOP====================
void loop() {
  //Blinks onboard diagnostic LED during execution
  digitalWrite(LED_BUILTIN, HIGH);
  delay(EXECUTING_LED_BLINK_FREQUENCY);
  digitalWrite(LED_BUILTIN, LOW);
  delay(EXECUTING_LED_BLINK_FREQUENCY);

  //Applies intelligent motor control based on PWM input and SILHOUETTE state
  analogWrite(ENA_LEFT, SILHOUETTE_SPEED);
  analogWrite(ENA_RIGHT, SILHOUETTE_SPEED);
  setSilhouetteState(SILHOUETTE_STATE);
}

//====================FUNCTIONAL METHODS====================
void setSilhouetteState(SilhouetteState silhouetteState) {
  switch (silhouetteState) {
    case FORWARD:
      digitalWrite(IN1, LOW); digitalWrite(IN2, HIGH);
      digitalWrite(IN3, LOW); digitalWrite(IN4, HIGH);
      break;

    case BACKWARD:
      digitalWrite(IN1, HIGH); digitalWrite(IN2, LOW);
      digitalWrite(IN3, HIGH); digitalWrite(IN4, LOW);
      break;

    case BRAKE:
      digitalWrite(IN1, HIGH); digitalWrite(IN2, HIGH);
      digitalWrite(IN3, HIGH); digitalWrite(IN4, HIGH);
      break;

    case COAST:
      digitalWrite(IN1, LOW); digitalWrite(IN2, LOW);
      digitalWrite(IN3, LOW); digitalWrite(IN4, LOW);
      break;

    default:
      digitalWrite(IN1, LOW); digitalWrite(IN2, LOW);
      digitalWrite(IN3, LOW); digitalWrite(IN4, LOW);
      break;
  }
}
