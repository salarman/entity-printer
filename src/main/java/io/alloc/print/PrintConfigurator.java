package io.alloc.print;

import io.alloc.constant.BuilderType;
import io.alloc.constant.PrintOption;
import io.alloc.util.CommonUtils;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public final class PrintConfigurator<I> {

    private final Set<PrintOption> options;
    private BuilderType builderType;
    private List<I> activateIndexes;
    private DateTimeFormatter dateTimeFormatter;

    private PrintConfigurator() {
        this.builderType = BuilderType.ROW;
        this.options = new HashSet<>();
    }

    private PrintConfigurator(BuilderType builderType) {
        this.builderType = builderType;
        this.options = new HashSet<>();
    }

    public static <I> PrintConfigurator<I> create(BuilderType builderType) {
        return new PrintConfigurator<>(builderType);
    }

    public static <I> PrintConfigurator<I> create() {
        return new PrintConfigurator<>(BuilderType.ROW);
    }

    public BuilderType getBuilderType() {
        return this.builderType;
    }

    public Set<PrintOption> getOptions() {
        return this.options;
    }

    public List<I> getActivateIndexes() {
        return this.activateIndexes;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return this.dateTimeFormatter;
    }

    public PrintConfigurator<I> builderType(final BuilderType builderType) {
        this.builderType = builderType;
        return this;
    }

    public PrintConfigurator<I> excludeDataType() {
        this.options.add(PrintOption.NO_DATA_TYPE);
        return this;
    }

    @SafeVarargs
    public final PrintConfigurator<I> activateFields(final I... fieldIndexes) {
        this.activateIndexes = CommonUtils.isNull(fieldIndexes) ? new ArrayList<>() : Arrays.stream(fieldIndexes)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        this.options.add(PrintOption.EXCEPT_COLUMN);
        return this;
    }


    public PrintConfigurator<I> dateformat(final String pattern) {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        this.options.add(PrintOption.DATE_FORMAT);
        return this;
    }

    public PrintConfigurator<I> allowMultiLine() {
        this.options.add(PrintOption.ALLOW_MULTILINE);
        return this;
    }

    public PrintConfigurator<I> withoutFloor() {
        this.options.add(PrintOption.WITHOUT_EACH_BORDER_BOTTOM);
        return this;
    }

    public PrintConfigurator<I> applyAll(PrintOption... printOptions) {
        this.options.addAll(Arrays.asList(printOptions));
        return this;
    }

    public PrintConfigurator<I> noEscape() {
        this.options.add(PrintOption.NO_ESCAPE);
        return this;
    }

    public PrintConfigurator<I> noEllipsis() {
        this.options.add(PrintOption.NO_ELLIPSIS);
        return this;
    }
}
