package com.scriptpoin.gestacaosaudavel.filter;

import android.text.InputFilter;
import android.text.Spanned;

public abstract class FiltraInput {

    public static InputFilter filtroDoInput() {
        // FILTRO QUE EVITA O USO DE ESPAÇOS DURANTE A DIGITAÇÃO
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++)
                    if (Character.isSpaceChar(source.charAt(i)))
                        return "";

                return null;
            }
        };
    }

}
