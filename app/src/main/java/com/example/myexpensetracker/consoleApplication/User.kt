package com.example.myexpensetracker.consoleApplication

class User (
    private var userName: String,
    private var userEmail: String,
    private var password : String
)
{
    val userId : Int = generateUserId()
    private var income : Float = 0F
    private var amountSpend : Float = 0F // Total amount spend as expense

    // This object is used to generate automatic User ID everyTime Creating new  User Object
    companion object
    {
        private var idGenerator : Int = 0
        fun generateUserId(): Int
        {
            return ++idGenerator
        }
    }
    // Getter Method to access private variable user Name
    fun getUserName() : String
    {
        return this.userName
    }
    // Getter Method to access private variable user Phone number
    fun getUserEmail() : String
    {
        return this.userEmail
    }

    // Setter methods to set value for the private member User name
    fun setName(name : String) : Response<User>
    {
        if (name.isBlank())
        {
            return Response(responseCode = ERROR_CODE, data = null, successMessage = "USer Name cannot be blank")
        }
        this.userName = name
        return Response(responseCode = SUCCESS_CODE, data = this, successMessage = "User Name Updated")
    }

    fun setIncome(income: Float) : Response<User>
    {
        if (income <= 0)  {
            return Response(responseCode = ERROR_CODE, data = null, errorMessage = "USer Income cannot be Zero/Negative")
        }
        this.income = income
        return Response(SUCCESS_CODE, this, "User Income Updated", null)
    }

    // Secure password update
    fun updatePassword(oldPassword: String, newPassword: String) : Response<User>
    {
        if (isPasswordCorrect(oldPassword)) {
            return Response(responseCode = ERROR_CODE, data = null, errorMessage = "Old Password Not Matched")
        }
        this.password = newPassword
        return Response(responseCode = SUCCESS_CODE, data = this, successMessage = "User Password Updated")
    }

    //Add amount spend function update the amountSpend field. @param Amount spend can be negative
    // as if expense record deleted The amount need to be reduced from amount spend
    fun addAmountSpend(amount: Float)
    {
        this.amountSpend += amount
    }

    // Method to verify the password of the user
    fun isPasswordCorrect(password : String) :Boolean
    {
        return this.password == password
    }

    // Method to display User Object Details
    fun displayUserDetails()
    {
        println("\n========================= User Details =========================")
        println("User ID        : $userId")
        println("User Name      : $userName")
        println("Income         : $$income")
        println("Amount Spent   : $$amountSpend")
        println("Remaining      : $${income - amountSpend}")
        println("==============================================================\n")  // To display details in well structured format
    }


}