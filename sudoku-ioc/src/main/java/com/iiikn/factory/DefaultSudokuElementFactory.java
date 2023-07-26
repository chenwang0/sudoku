package com.iiikn.factory;

import com.iiikn.util.ForeachFunction;

import java.util.Collection;

public class DefaultSudokuElementFactory extends SudokuElementFactory {

    @Override
    public boolean isExtender(Object obj) {
        return false;
    }

    @Override
    public void foreach(ForeachFunction<Object> function) {

    }

    @Override
    public Collection<Object> getAllExtenderFactory() {
        return null;
    }
}
