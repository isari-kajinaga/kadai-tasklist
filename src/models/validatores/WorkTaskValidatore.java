package models.validatores;

import java.util.ArrayList;
import java.util.List;

import models.WorkTask;

public class WorkTaskValidatore {
    // バリデーションを実行する
    public static List<String> validate(WorkTask w) {
        List<String> errors = new ArrayList<String>();


        String content_error = validateContent(w.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }


        return errors;
    }

    // タスクの必須入力チェック
    private static String validateContent(String content) {
        if(content == null || content.equals("")) {
            return "タスクを入力してください。";
        }

        return "";
    }

}