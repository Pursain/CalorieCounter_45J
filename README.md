# CalorieCounter_45J

-------------------
Calorie Counter App

4 methods

Method createFood: Input all the food items and their calories

Function: add up all the calories, and subtract if someone makes a mistake, kind of like a mini calculator

Method addEntry: The app would count up the calories, maybe divide into Breakfast, Lunch, Dinner

Method controlWeight: If they want to control their weight, put in current weight and weight they want to be, and track that

Method createAccount: User log in, or sign up, on one page
        First name, last name
        Email
        Password
        Gender
        Height
        Weight
        Goal type: lose weight, maintain weight, gain weight
 
Method trackExercise:Track exercise activities

2 Objects
1. User when they input their information
2. Food items and calories
    
Database (Google Firebase)
Store all the food with the calories, unique id for each food item

Possible functionalities/Activities:
1. Adding new food item not already in the database
2. Adding up calories
3. See if user has reached calorie goal
4. Show how many calories they ate on average per day over the course of a month

How the app will look 
First Page: List of previous dates that they have added entries



#THINGS TO DO CHECKLIST

## Login page
- [x] UI design
- [ ] existing login
        if already exists, pass username through intent
- [ ] new login
        create a new firebase entry under the user branch and pass username through intent
- [ ] click to move to UserView
        Use intent, pass username!!
        
## UserView Main Page
- [ ] UI design
        - [ ] dynamic title, use intent info passed from above page
        
## Add Food Page
- [x] UI design
- [ ] Add food to firebase
        if exist, update quanity
        if not exist, add food, then add quanity attribute and calorie attribute
- [ ] Delete food
        if exist, update quanity, if quanity = zero



