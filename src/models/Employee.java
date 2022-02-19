package models;

public class Employee {

    private int id;
    private String fName;
    private String lName;
    private String email;
    private String noTelp;
    private String hireDate;
    private String job;
    private Double salary;
    private Double commission;
    private int manager;
    private int department;

    public Employee() {

    }

    public Employee(int id, String fName, String lName, String email, String noTelp, String hireDate,
                    String job, Double salary, Double commission, int manager, int department) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.noTelp = noTelp;
        this.hireDate = hireDate;
        this.job = job;
        this.salary = salary;
        this.commission = commission;
        this.manager = manager;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", noTelp='" + noTelp + '\'' +
                ", hireDate='" + hireDate + '\'' +
                ", job='" + job + '\'' +
                ", salary=" + salary +
                ", commission=" + commission +
                ", manager=" + manager +
                ", department=" + department +
                '}';
    }
}
