# 💰 Money Manager — Personal Finance Tracker

**Money Manager** is a full-stack personal finance management application built with **React**, **Tailwind CSS**, and **Spring Boot**. It allows users to track income and expenses, manage categories, and apply detailed filters to analyze their financial data. The app includes secure authentication, email activation, responsive design, and Excel export/email functionality for incomes and expenses.

---

## 🌐 Live Demo

* **Frontend (Netlify):** [https://astonishing-rugelach-8e59b3.netlify.app/](https://astonishing-rugelach-8e59b3.netlify.app/)
* **Backend (Render/Railway):** [https://money-manager-fullstack.onrender.com/](https://money-manager-fullstack.onrender.com/)

---

## ⚙️ Tech Stack

**Frontend**

* React 18+
* Tailwind CSS
* Axios
* React Router DOM
* Context API (for authentication and global state)

**Backend**

* Java 17+
* Spring Boot 3+
* Spring Security (JWT Authentication)
* Spring Data JPA
* MySQL (or PostgreSQL)
* JavaMailSender (for email verification and sending Excel attachments)

**Dev Tools**

* Maven
* Docker (optional)
* GitHub Actions (optional CI/CD)

---

## 🧭 Application Flow

1. User **registers** with email and password.
2. Backend sends an **activation email** with a token.
3. User **activates** the account by clicking the email link.
4. User **logs in** and receives a JWT.
5. Authenticated user is redirected to the **Dashboard**.
6. User must **create at least one category** before adding income or expenses.
7. Users can **add / delete** income and expense records.
8. **Filter page** allows filtering by category, type, order, start date, end date, or search keyword.
9. Users can **download Excel files** for incomes/expenses or have them **emailed directly**.
10. Non-authenticated users **cannot access protected pages**.

---

## 🧩 Key Features

* 🔐 Secure Authentication (JWT + Email Activation)
* 📊 Income & Expense Management
* 🗂️ Category Creation (mandatory before transactions)
* 🔎 Dynamic Filtering by Category, Type, Date, Keyword
* 📈 Dashboard Overview (Total Income, Expense, Balance)
* 📧 Excel Export & Email for Income/Expense Data
* 🌙 Fully Responsive UI with Tailwind CSS
* 💡 Lucide React Icons for modern design
* 🧠 Clean and scalable architecture

---

## 🧱 Project Structure

```plaintext
money-manager/
│
├── backend/             # Spring Boot API
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   └── security/
│
├── frontend/            # React App
│   ├── src/
│   │   ├── pages/       # Register, Login, Dashboard, Income, Expense, Filter
│   │   ├── components/  # Shared UI components
│   │   ├── context/     # AppContext for global state
│   │   ├── util/        # API endpoints & helpers
│   │   └── App.js
│   └── package.json
│
└── README.md
```

---

## 🧾 API Endpoints (Sample)

All endpoints are prefixed with `/api/v1.0`

**Auth & Profile**

* `POST /api/v1.0/register` → Register user, send activation email
* `GET /api/v1.0/active?token=` → Activate user
* `POST /api/v1.0/login` → Login and return JWT
* `GET /api/v1.0/profile` → Get public profile info

**CategoryController**

* `POST /api/v1.0/categories` → Create category
* `GET /api/v1.0/categories` → List user categories
* `GET /api/v1.0/categories/{type}` → List categories by type
* `PUT /api/v1.0/categories/{categoryId}` → Update category

**IncomeController**

* `POST /api/v1.0/incomes` → Add income
* `GET /api/v1.0/incomes` → Get current month incomes
* `GET /api/v1.0/incomes/latest` → Get latest 5 incomes
* `GET /api/v1.0/incomes/total` → Get total income
* `DELETE /api/v1.0/incomes/{incomeId}` → Delete income

**ExpenseController**

* `POST /api/v1.0/expenses` → Add expense
* `GET /api/v1.0/expenses` → Get current month expenses
* `GET /api/v1.0/expenses/latest` → Get latest 5 expenses
* `GET /api/v1.0/expenses/total` → Get total expense
* `DELETE /api/v1.0/expenses/{expenseId}` → Delete expense

**FilterController**

* `POST /api/v1.0/filter` → Filter transactions by type, date range, keyword, category, and sort order

**DashboardController**

* `GET /api/v1.0/dashboard` → Get dashboard summary data

**EmailController**

* `GET /api/v1.0/email/income-excel` → Send income Excel via email
* `GET /api/v1.0/email/expense-excel` → Send expense Excel via email

**ExcelController**

* `GET /api/v1.0/excel/download/income` → Download income Excel
* `GET /api/v1.0/excel/download/expense` → Download expense Excel

**HomeController**

* `GET /api/v1.0/status` or `/api/v1.0/health` → Health check

---

## 🧩 Setup & Installation

### Backend (Spring Boot)

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

**Environment Variables (for testing only):**

```env
EMAIL_USERNAME=your_email@example.com
EMAIL_PASSWORD=your_email_password
JWT_SECRET=your_jwt_secret
MONEY_MANAGER_BACKEND_URL=http://localhost:8080
MONEY_MANAGER_FRONTEND_URL=http://localhost:5173
MYSQL_DB_URL=jdbc:mysql://localhost:3306/moneymanager
MYSQL_USERNAME=root
MYSQL_PASSWORD=your_mysql_password
POSTGRES_DB_URL_RENDER=your_postgres_url
POSTGRES_USERNAME_RENDER=your_postgres_user
POSTGRES_PASSWORD_RENDER=your_postgres_password
SMTP_HOST=smtp.gmail.com
SMTP_PORT=587
```

### Frontend (React)

```bash
cd frontend
npm install
npm run dev
```

**.env file:**

```env
VITE_API_BASE_URL=http://localhost:8080/api/v1.0
```

---

## 🚀 Deployment

* **Frontend (Live):** [Netlify](https://astonishing-rugelach-8e59b3.netlify.app/)
* **Backend (Live):** [Railway](https://money-manager-fullstack.onrender.com/)
* Use HTTPS and secure JWT secrets (via environment variables or Secrets Manager)

---

## 🧪 Testing

* **Backend:** JUnit + Mockito for services, integration tests for controllers
* **Frontend:** React Testing Library + Jest for UI validation
* **Excel Export/Email:** Test sending and downloading Excel files for both incomes and expenses

---

## 📚 License

This project is licensed under the **MIT License**.

---

### ✨ Summary

Money Manager provides a clean, modern, and complete full-stack architecture to build a personal finance app. It’s the perfect starting point for developers aiming to learn React + Spring Boot integration or launch a SaaS product quickly, with additional Excel export and email functionality.
