package com.example.myexpensetracker.consoleApplication
import java.util.Scanner

class ExpenseTracker
{
    private var userList: MutableList<User> = mutableListOf()
    private var expenseCategoryList: MutableList<ExpenseCategory> = mutableListOf()
    private var expenseRecordList: MutableList<ExpenseRecord> = mutableListOf()

    // Initializing the uncategorized expense as default expense while starting the expenseTrackerApplication
    init
    {
        val expenseCategory = ExpenseCategory("uncategorized", "This type is default type")
        expenseCategoryList.add(expenseCategory)
    }

    //Utility Methods
    // Get user from List using User Id
    private fun getUserFromList(userId: Int? = null, email : String? = null): User?
    {
        return when {
            userId != null -> userList.find { it.userId == userId }
            email != null -> userList.find { it.getUserEmail() == email }
            else -> null  // Handle the case where both userId and email are null
        }
    }
    
//    private fun getUserFromList (userId : Int) : User?
//    {
//        return userList.find { it.userId == userId }
//    }
//    private fun getUserFromList (email : String) : User?
//    {
//        return userList.find { it.getUserEmail() == email }
//    }
    
    
    // Method to Create new user. It takes Name and Password as input and check
    private fun signUpUser(name: String, email : String, password: String): Response<User?>
    {
        // Create new User and return the user Id
        if (getUserFromList(email = email) != null)
        {
            return Response(responseCode = ERROR_CODE, data = null, errorMessage = "User Already exists. Please login.")
        }
        val user = User(name, email, password)
        userList.add(user)
        return Response(responseCode = SUCCESS_CODE, data = user, successMessage = "User created successfully.")
    }

    // Method to check user credential is correct or not
    private fun checkUserCredential(email: String, password: String): Response<User>
    {
        // Check if the user is already sign in or not
        val user = getUserFromList(email = email) ?: return Response(responseCode = NOT_FOUND, data = null, errorMessage = "User Not Signed In")
        return if (user.isPasswordCorrect(password))
        {
            Response(responseCode = SUCCESS_CODE, data = user, successMessage = "User Credentials Verified")
        }
        else
        {
            Response(responseCode = WRONG_VALUE, data = null, errorMessage = "Wrong Password")
        }
    }

    fun mainMenu(scanner: Scanner)
    {
        println("Welcome to Personal Expense Tracker")
        // Menu driven to handle User Login and Sign In
        while (true)
        {
            println("\n===== Menu =====")
            println("Enter the corresponding number to perform that Action")
            println("1 -> Sign Up")
            println("2 -> Login")
            println("3 -> Exit")
            print("Enter your choice: ")
            when (InputValidator.getInteger(scanner, "Option"))
            {
                // SignUp User Section
                1 -> {
                    signUpFlow(scanner)
                }
                // Login User Section
                2 -> {
                    print("Enter Email:")
                    scanner.nextLine() // To clear buffer in console
                    val email = scanner.nextLine()
                    print("Enter Password: ")
                    val password = scanner.nextLine()
                    val response = checkUserCredential(email, password)
                    when (response.responseCode)
                    {
                       SUCCESS_CODE -> {
                            println(response.successMessage)
                            response.data?.let { userDetailMenu(scanner, userId = it.userId) } // Check for data is not null
                        }

                        NOT_FOUND -> {
                            println(response.errorMessage)
                            signUpFlow(scanner)
                        }
                        else -> { println(response.errorMessage) }
                    }

                }
                3 -> {
                    println("Exiting program. Goodbye!")
                    break
                }

                else -> println("Invalid choice. Please enter a valid option.")
            }
        }
    }

    private fun signUpFlow(scanner: Scanner)
    {
        print("Enter User Name: ")
        scanner.nextLine() // To clear buffer in console
        val userName = scanner.nextLine()
        print("Enter Email: ")
        val email = scanner.nextLine()
        print("Enter Password: ")
        val password = scanner.nextLine()
        val response = signUpUser(userName, email, password)
        if (response.responseCode == SUCCESS_CODE)
        {
            println(response.successMessage)
            response.data?.displayUserDetails() // Display User Details To let the user to known
            response.data?.let { userDetailMenu(scanner, userId = it.userId) }
        }
        else
        {
            println(response.errorMessage)
        }

    }

    private fun userDetailMenu( scanner: Scanner, userId: Int)
    {
        // Menu driven to handle User Login and Sign In
        while (true)
        {
            println("\n===== Main Menu =====")
            println("Enter the corresponding number to perform that Action")
            println("1 -> User Settings")
            println("2 -> Record Expense")
            println("3 -> Logout")
            print("Enter your choice: ")
            when (InputValidator.getInteger(scanner, "Option"))
            {
                // Display User Details
                1 -> {
                    userSettingsMenu( scanner, userId)
                }
                // Record Expense Menu part
                2 -> {
                    recordUserExpense( scanner, userId)
                }
                // User Logout option part
                3 -> {
                    println("Logout!!!")
                    break
                }
                else -> {
                    println("Invalid choice. Please enter a valid option.")
                }
            }
        }
    }
}

fun main()
{
    println("Welcome to Personal Expense Tracker")
    // Initialization
    val scanner = Scanner(System.`in`)
    val expenseTrackerApp1 = ExpenseTracker()
    //expenseTrackerApp1.main(scanner)
}