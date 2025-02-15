package com.example.myexpensetracker.consoleApplication

import java.util.Scanner

object InputValidator
{

    fun getInteger(scanner: Scanner, prompt: String): Int
    {
        while (true)
        {
            if (scanner.hasNextInt())
            {
                return scanner.nextInt()
            }
            println("Invalid Input. Please enter a $prompt")
            scanner.next()  // Consume invalid input
        }
    }
    // Function to check User enter the correct input format
    private fun getFloat(scanner: Scanner, prompt: String): Float
    {
        while (true)
        {
            if (scanner.hasNextFloat())
            {
                return scanner.nextFloat()
            }
            println("Invalid Input. Please enter a $prompt")
            scanner.next()  // Consume invalid input
        }
    }
}