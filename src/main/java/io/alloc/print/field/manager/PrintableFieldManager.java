package io.alloc.print.field.manager;

import java.util.List;

public interface PrintableFieldManager<I, F> {

    void activatePrintableFields(List<I> fieldIndexes);

    F [] getActivatedFields();

    Class<?> getTypeClass();

    boolean hasNoActivateFields();
}
