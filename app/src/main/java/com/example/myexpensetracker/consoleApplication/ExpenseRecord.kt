package com.example.myexpensetracker.consoleApplication

class ExpenseRecord (
    private var expenseCategoryId : Int,
    private var userId : Int,
    private var amount : Float,
    private var date: String,
    private var description: String = ""
)
{
    val recordId: Int = generateRecordId()
    //This object is used to generate automatic Record ID everyTime Creating new  Record Object
    companion object
    {
        private var idGenerator: Int = 0
        fun generateRecordId(): Int
        {
            return ++idGenerator
        }
    }

    fun getUserId() :Int
    {
        return userId
    }
    fun getAmountSpend () : Float
    {
        return this.amount
    }

    // Setter Function
    fun setRecordAmount (amount: Float) : Response<ExpenseRecord>
    {
        if (amount <= 0)  {
            return Response(FAILURE_CODE, null, null, "Amount cannot be Zero/Negative")
        }
        this.amount = amount
        return Response(SUCCESS_CODE, this, "User Income Updated", null)
    }
    // Setter function
    fun setRecordDate(date : String) : Response<ExpenseRecord>
    {
        if (date.isBlank())
        {
            return Response(FAILURE_CODE, null, null, "Date cannot be blank")
        }
        this.date = date
        return Response(SUCCESS_CODE, this, "Date Updated", null)
    }

    //Method to display Record Object Details
    fun displayExpenseDetails()
    {
        println("Record ID     : $recordId")
        println("Expense ID    : $expenseCategoryId")
        println("User ID       : $userId")
        println("Amount        : $$amount")
        println("Date          : $date")
        println("Description   : $description")
        println("------------------------------------------\n") // To display details in well structured format
    }
}