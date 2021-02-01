package com.sergway.trivia.data;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.sergway.trivia.controller.AppController;
import com.sergway.trivia.model.Question;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    ArrayList<Question> questionArrayList = new ArrayList<>();

    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements.json";

    public List<Question> getQuestions(final AnswerListAsyncResponse callback) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONArray item = response.getJSONArray(i);
                            questionArrayList.add(new Question(item.getString(0), item.getBoolean(1)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                        callback.processFinished(questionArrayList);
                }, error -> {
        });


        AppController.getInstance().addToRequestQueue((jsonArrayRequest));

        return questionArrayList;
    }

}
