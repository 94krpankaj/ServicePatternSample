# Starter Pattern- Android

Starting a Activity from another Activity is very simple in Android using Intent.
Following is an example in which we are going from MainActivity to DetailsActivity.

## MainActivity.kt

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    button.setOnClickListener {
        if(editText.text.isNotEmpty()){
            startActivity(Intent(this,DetailsActivity::class.java)
                .putExtra("money",editText.text.toString()))
        }
    }
}
```

## DetailsActivity.kt

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_details)

    val intent = intent
    textView.text ="Hi bro!! Thanks for sending me " +intent.getStringExtra("money")+" $"
}
```

That's a piece of cake. Yes?

Now think of a situation where you have one more activity with name SecondActivity. And you can also start DetailsActivity from there too. Which can be done simply as done above.
Now there are chances of runtime exception when you forgot to putExtra from the SecondActivity which can be because of two or more developers working on same project or may be error by a developer. 
In this situation you wont't face any compile time error. The only time error will occur when you start DetailsActivity from SecondActivity without putExtra as the SecondActivity is expecting extras with Intent. 

## SecondActivity.kt

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_second)

    button.setOnClickListener {
        if(editText.text.isNotEmpty()){
            startActivity(
                Intent(this,DetailsActivity::class.java)
            )
        }
    }
}
```

To sort this problem you can follow Starter Pattern. It can be used to keep such runtime errors in check and give users a better experience while using your app.
To use this pattern you can do something as follows

## DetailsActivity.kt

Define a function inside companion object in DetailsActivity. And simply call it from wherever you want to start this Activity.

```kotlin
companion object{
    fun getDetailsActivityIntent(context: Context,money:String){
        context.startActivity(Intent( context,DetailsActivity::class.java)
            .putExtra("money",money))
    }
}
```

## MainActivity.kt

```kotlin

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    button.setOnClickListener {
        if(editText.text.isNotEmpty()){
            DetailsActivity.getDetailsActivityIntent(this,editText.text.toString())
        }
    }
}
```
So this way you can remove any possibility of runtime error. As this will give you a compile time error if there is any problem with parameter. It is good programming practise.



## License
[MIT](https://choosealicense.com/licenses/mit/)
