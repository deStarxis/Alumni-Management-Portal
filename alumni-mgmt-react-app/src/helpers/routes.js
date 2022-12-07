import AddressForm from "../components/address/AddressForm";
import CompanyForm from "../components/company/CompanyForm";
import CVForm from "../components/cv/CVForm";
import JobExperienceForm from "../components/job-experience/JobExperienceForm";
import JobForm from "../components/job/JobForm";
import ForgotPassword from "../pages/ForgotPassword";
import Home from "../pages/Home";
import Job from "../pages/job/Job";
import Jobs from "../pages/job/Jobs";
import Login from "../pages/Login";
import Profile from "../pages/Profile";
import ResetPassword from "../pages/ResetPassword";
import Signup from "../pages/Signup";
import Student from "../pages/student/Student";
import Students from "../pages/student/Students";

const publicRoutes = [
  {
    name: "Login",
    type: "default",
    onNavBar: true,
    key: "login",
    route: "/login",
    component: <Login />,
    private: false,
  },
  {
    name: "Sign Up",
    type: "default",
    onNavBar: true,
    key: "signup",
    route: "/signup",
    component: <Signup />,
    private: false,
  },
  {
    name: "Forgot Password",
    type: "default",
    onNavBar: false,
    key: "forgotPassword",
    route: "/forgot-password",
    component: <ForgotPassword />,
    private: false,
  },
  {
    name: "Reset Password",
    type: "default",
    onNavBar: false,
    key: "resetPassword",
    route: "/reset-password/:resetToken",
    component: <ResetPassword />,
    private: false,
  },
];

const privateRoutes = [
  {
    name: "Home",
    type: "default",
    onNavBar: true,
    key: "home",
    route: "/",
    component: <Home />,
    private: true,
  },
  {
    name: "Add Job",
    type: "default",
    onNavBar: false,
    key: "addJob",
    route: "/jobs/add",
    component: <JobForm />,
    private: true,
  },

  {
    name: "Add Company",
    type: "default",
    onNavBar: false,
    key: "addCompany",
    route: "/add-company",
    component: <CompanyForm />,
    private: true,
  },
  {
    name: "Add CV",
    type: "default",
    onNavBar: false,
    key: "addCV",
    route: "/add-cv",
    component: <CVForm />,
    private: true,
  },
  {
    name: "Add Job Experience",
    type: "default",
    onNavBar: false,
    key: "addJobExperience",
    route: "/add-job-experience",
    component: <JobExperienceForm />,
    private: true,
  },
  {
    name: "Students",
    type: "default",
    roles: ["admin", "faculty"],
    onNavBar: true,
    key: "students",
    route: "/students",
    component: <Students />,
    private: true,
  },
  {
    name: "Student",
    type: "default",
    onNavBar: false,
    key: "student",
    route: "/students/:id",
    component: <Student />,
    private: true,
  },
  {
    name: "Jobs",
    type: "default",
    onNavBar: true,
    key: "jobs",
    route: "/jobs/all",
    roles: ["student", "faculty"],
    component: <Jobs />,
    private: true,
  },
  {
    name: "Job",
    type: "default",
    onNavBar: false,
    key: "job",
    route: "/jobs/all/:id",
    component: <Job />,
    private: true,
  },
  {
    name: "My Jobs",
    type: "default",
    onNavBar: false,
    key: "myJobs",
    route: "/jobs/:task",
    component: <Jobs />,
    private: true,
  },
  {
    name: "Applied Jobs",
    type: "default",
    onNavBar: false,
    key: "appliedJobs",
    route: "/jobs/:task",
    component: <Jobs />,
    private: true,
  },
  {
    name: "Job",
    type: "default",
    onNavBar: false,
    key: "job",
    route: "/jobs/:id",
    component: <Job />,
    private: true,
  },
  {
    name: "Job",
    type: "default",
    onNavBar: false,
    key: "job",
    route: "/jobs/update/:id",
    component: <JobForm />,
    private: true,
  },
  {
    name: "Profile",
    type: "default",
    onNavBar: false,
    key: "profile",
    route: "/profile",
    component: <Profile />,
    private: true,
  },
  {
    name: "Address",
    type: "default",
    onNavBar: false,
    key: "address",
    route: "/profile/address/:task/:id",
    component: <AddressForm />,
    private: true,
  },
];

const routes = { privateRoutes, publicRoutes };

export default routes;
