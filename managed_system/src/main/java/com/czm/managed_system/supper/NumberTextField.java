package com.czm.managed_system.supper;

import javafx.scene.control.TextField;

/**
 * @Author CZM
 * @create 2020/6/25 17:28
 */
public class NumberTextField extends TextField {

    @Override
    public void replaceText(int start, int end, String text)
    {
        if (validate(text))
        {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text)
    {
        if (validate(text))
        {
            super.replaceSelection(text);
        }
    }

    private boolean validate(String text)
    {
        return text.matches("[0-9]*");
    }
}