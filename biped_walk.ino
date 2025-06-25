//====================SILHOUETTE====================

//Arduino Documentation Examples: https://docs.arduino.cc/built-in-examples/
//Arduino Web API Link: https://docs.arduino.cc/learn/programming/reference/

//Programming Executing (Debugging LED)
const int EXECUTING_LED_BLINK_DELAY = 100; //Value is in milliseconds (divide by 1000 for value in seconds)

//Left Leg (Motor - L)
const int IN1 = 6; //Controls Direction
const int IN2 = 7; //Works with IN1
const int ENL = 8; //Controls Speed via PWM

//Right Leg (Motor - B)
const int IN3 = 10; //Controls Direction
const int IN4 = 11; //Works with IN3
const int ENR = 3; //Controls Speed via PWM

//Speed Adjustments
int LEFT_MOTOR_SPEED = 200; //PWM signal ranges from 0 to 255 inclusive, 255 being full power
int RIGHT_MOTOR_SPEED = 200; //PWM signal ranges from 0 to 255 inclusive, 255 being full power
int FIRST_STEP_TIME_OFFSET = 500; //Value is in milliseconds (divide by 1000 for value in seconds)

void setup() {
  pinMode(LED_BUILTIN, OUTPUT);

  pinMode(IN1, OUTPUT); //Sets IN1 as an output pin
  pinMode(IN2, OUTPUT); //Sets IN2 as an output pin
  pinMode(ENL, OUTPUT); //Sets ENL as an output pin
  
  pinMode(IN3, OUTPUT); //Sets IN3 L as an output pin
  pinMode(IN4, OUTPUT); //Sets IN4 as an output pin
  pinMode(ENR, OUTPUT); //Sets ENR as an output pin
}

void loop() {
  digitalWrite(LED_BUILTIN, HIGH);  // turn the LED on (HIGH is the voltage level)
  delay(EXECUTING_LED_BLINK_DELAY);                      // wait for a second
  digitalWrite(LED_BUILTIN, LOW);   // turn the LED off by making the voltage LOW
  delay(EXECUTING_LED_BLINK_DELAY);   

  digitalWrite(IN1, HIGH); //Sets IN1 HIGH (on) to go forward
  digitalWrite(IN2, LOW); //Sets IN2 LOW (off) to guarantee forward direction
  analogWrite(ENL, LEFT_MOTOR_SPEED); //PWM signal ranges from 0 to 255 inclusive, 255 being full power

  //delay(FIRST_STEP_TIME_OFFSET);

  digitalWrite(IN3, HIGH); //Sets IN3 HIGH (on) to go forward
  digitalWrite(IN4, LOW); //Sets IN4 LOW (off) to guarantee forward direction
  analogWrite(ENR, RIGHT_MOTOR_SPEED); //PWM signal ranges from 0 to 255 inclusive, 255 being full power
}
