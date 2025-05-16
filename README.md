* 🔄 Workflow explanation
* ✅ Step-by-step setup
* 📊 Visual architecture diagram (ASCII for now, but can be converted to image later)
* 📡 API documentation
* 📁 File structure

--------------

🚀 Spring Boot Project: Email & SMS Notification using AWS SNS

This project is a Spring Boot application that allows you to send Email and SMS notifications using Amazon SNS (Simple Notification Service).

---

🧠 Workflow Overview

```

User / Client
    |
    | [REST API POST]
    v
Spring Boot Controller
    |
    v
SNS Service Layer (Email / SMS)
    |
    v
SNS Client (AWS SDK v2)
    |
    v
AWS SNS (Cloud)
    |
    v
Email Inbox / Mobile Device

````

✉️ Email Flow
1. `/api/email/send` endpoint receives subject + message.
2. `SnsMailService` builds SNS `PublishRequest`.
3. AWS SNS publishes the email to the verified recipients.

SMS Flow
1. `/api/sms/send` endpoint receives number + message.
2. `SnsSmsService` constructs and sends a `PublishRequest` for SMS.
3. AWS SNS delivers SMS to the target phone number.

---

🛠️ Setup Instructions

✅ Prerequisites

- Java 17+
- Maven
- AWS account with SNS Full access
- Verified phone numbers/emails in **SNS Sandbox**
- Spring Boot 3.x compatible setup

---

🧾 Steps to Run

1. 📥 Clone the repository

```bash
git clone https://github.com/your-username/aws-sns-springboot.git
cd aws-sns-springboot
````

2. 🔑 Set AWS Credentials in `application.properties`

```properties
aws.accessKey=YOUR_ACCESS_KEY
aws.secretKey=YOUR_SECRET_KEY
aws.region=us-east-1
```

3. ▶️ Run the application

```bash
./mvnw spring-boot:run
```

---

📡 API Endpoints

✉️ Send Email

* POST `/api/email/send`
* Query Parameters:

  * `subject` - Subject line
  * `message` - Email message
* Example:

```
POST http://localhost:8080/api/email/send?subject=Hello&message=Hello+from+SNS
```

---

Send SMS

* POST `/api/sms/send`
* Query Parameters:

  * `number` - Phone number (e.g., `+15555555555`)
  * `message` - Text content
* Example:

```
POST http://localhost:8080/api/sms/send?number=+15555555555&message=Test+SMS
```

---

📂 Project Structure

```
src/main/java/com/example/demo
│
├── config
│   └── AwsConfig.java            # AWS SNS Client configuration
│
├── controller
│   ├── EmailController.java      # REST API for email sending
│   └── SMSController.java        # REST API for SMS sending
│
├── service
│   ├── SnsMailService.java       # Email logic
│   └── SnsSmsService.java        # SMS logic
```

---

🔐 AWS SNS Notes

* Sandbox Mode requires all recipients (email/phone) to be verified in the AWS SNS Console.
* To move to production, submit a request to AWS to increase SMS/email sending limits.

---

🔍 Visual Representation

```````````````````````````````````````````````
                          ┌────────────────────┐
                          │   End User / API   │
                          └────────┬───────────┘
                                   │
                            REST Request (POST)
                                   ▼
                     ┌───────────────────────────┐
                     │     Spring Boot App       │
                     ├───────────────────────────┤
                     │ EmailController | SMSController
                     └────────┬────────┬────────┘
                              │        │
                              ▼        ▼
                     SnsMailService  SnsSmsService
                              │        │
                              └──┬─────┘
                                 ▼
                          AWS SNS Client
                                 ▼
                          AWS SNS (Cloud)
                          /             \
                 Email Delivery     SMS Delivery
```

---


