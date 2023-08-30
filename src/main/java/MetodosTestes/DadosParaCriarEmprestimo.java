package MetodosTestes;

public class DadosParaCriarEmprestimo {
    private String firstName;
    private String lastName;
    private String address1;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String dob;
    private String ssn;
    private String employer;
    private String phoneNumber;
    private String durationOfJob;
    private String income;
    private String loanTerm;
    private String loanAmount;
    private String validateAddress;

    public DadosParaCriarEmprestimo(String firstName, String lastName, String address1, String city,
                                    String state, String zip, String country, String dob, String ssn,
                                    String employer, String phoneNumber, String durationOfJob, String income,
                                    String loanTerm, String loanAmount, String validateAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.dob = dob;
        this.ssn = ssn;
        this.employer = employer;
        this.phoneNumber = phoneNumber;
        this.durationOfJob = durationOfJob;
        this.income = income;
        this.loanTerm = loanTerm;
        this.loanAmount = loanAmount;
        this.validateAddress = validateAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    public String getDob() {
        return dob;
    }

    public String getSsn() {
        return ssn;
    }

    public String getEmployer() {
        return employer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDurationOfJob() {
        return durationOfJob;
    }

    public String getIncome() {
        return income;
    }

    public String getLoanTerm() {
        return loanTerm;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public String getValidateAddress() {
        return validateAddress;
    }
}
