package com.example.todo

 class dataman{
     var users = mutableListOf<data>()

    fun insertdata(desc : String)
    {
         val user = data(desc)
        users.add(user)
    }
     fun removedata(desc : String)
     {
         val user = data(desc)
         users.remove(user)
     }
}
