package com.a17001922.wil_app;

/*
 NB!!! THIS WHOLE CLASS HAS TO DO STRICTLY WITH THE API CALLS AND GETTING OF AUTHORIZATION OF A USER TO LOGIN AND BE CREATED
 */

//#TODO TEST ALL METHODS IN THE CLASS TO SEE IF IT WORKS CORRECTLY WITH THE API
public class Connection {
    // #TODO IN ORDER TO TEST API LOCALLY CHANGE THE PORTION UNDER THIS TEXT TO YOUR MACHINES API ADDRESS


   /* private boolean registerAuth = false;
    private DailyObject Quote;
    private ReturnGoalObject goalsList;
    private Goal addGoal;
    //<editor-fold desc="Description">
    private Goal addingCustomGoal;
    //</editor-fold>
    private boolean flag;
    private static final String TAG = "ConnectionClass";
    LoginFragment obj = new LoginFragment();


    //________Register New User__________

    public boolean userRegister(RegisterUserObject user) {


        return registerAuth;
    }


    //________Login__________

    public void userLogin(final LoginUserObject user, final Context context) {


    }


    //________Get Daily Quote__________

    public DailyObject getDailyQuote() {
        Quote = new DailyObject();
        DailyQuoteService service = StaticClass.retrofit.create(DailyQuoteService.class);
        final Call<DailyObject> quoteCall = service.getQuote();
        quoteCall.enqueue(new Callback<DailyObject>() {
            @Override
            public void onResponse(Call<DailyObject> call, Response<DailyObject> response) {
                if (!response.isSuccessful()) {

                } else {
                    Quote = response.body();
                }
            }

            @Override
            public void onFailure(Call<DailyObject> call, Throwable t) {

            }
        });

        return Quote;
    }


    //________Get Goals__________

    public ReturnGoalObject getGoals(userGoalObject userGoals) {
        goalsList = new ReturnGoalObject();
        goalsService service = StaticClass.retrofit.create(goalsService.class);
        final Call<ReturnGoalObject> goalsCall = service.getGoalsList(userGoals);
        goalsCall.enqueue(new Callback<ReturnGoalObject>() {
            @Override
            public void onResponse(Call<ReturnGoalObject> call, Response<ReturnGoalObject> response) {
                if (!response.isSuccessful()) {

                } else {
                    goalsList = response.body();
                }
            }

            @Override
            public void onFailure(Call<ReturnGoalObject> call, Throwable t) {

            }
        });

        return goalsList;
    }


    //________Add User Goal__________

    /*public boolean addUserGoal(userGoalObject addingGoal) {
        flag = false;
        addGoal = new goals();
        goalsService service = StaticClass.retrofit.create(goalsService.class);
        final Call<goals> addGoalCall = service.addingGoal(addingGoal);
        addGoalCall.enqueue(new Callback<goals>() {
            @Override
            public void onResponse(Call<goals> call, Response<goals> response) {
                if (!response.isSuccessful()) {

                } else {
                    addGoal = response.body();
                    flag = true;
                }
            }

            @Override
            public void onFailure(Call<goals> call, Throwable t) {

            }
        });

        return flag;
    }


    //________Add Custom Goal__________

    public boolean addCustomGoal(CustomGoalObject addCustomGoal) {
        flag = false;
        addingCustomGoal = new goals();
        goalsService service = StaticClass.retrofit.create(goalsService.class);
        final Call<goals> customGoalObjectCall = service.addingCustomGoal(addCustomGoal);
        customGoalObjectCall.enqueue(new Callback<goals>() {
            @Override
            public void onResponse(Call<goals> call, Response<goals> response) {
                if (!response.isSuccessful()) {

                } else {
                   addingCustomGoal = response.body();
                    flag = true;
                }
            }

            @Override
            public void onFailure(Call<goals> call, Throwable t) {

            }
        });

        return flag;
    }
*/


}
