package com.example.myexpensetracker.consoleApplication



// Generic response class for consistent responses across the application
class Response <T>
    (
    val responseCode : Int, // Success (1) or Failure (-1)
    val data : T?, // Actual data being returned
    val successMessage : String? = null, // Holds a success message if operation succeed or null
    val errorMessage: String? = null // Holds a Failure message if operation Failed or null
)
