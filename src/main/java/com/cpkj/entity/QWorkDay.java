package com.cpkj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWorkDay is a Querydsl query type for WorkDay
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWorkDay extends EntityPathBase<WorkDay> {

    private static final long serialVersionUID = 574115669L;

    public static final QWorkDay workDay = new QWorkDay("workDay");

    public final DateTimePath<java.util.Date> createDay = createDateTime("createDay", java.util.Date.class);

    public final StringPath remark = createString("remark");

    public final DateTimePath<java.util.Date> subDay = createDateTime("subDay", java.util.Date.class);

    public final StringPath userId = createString("userId");

    public final ListPath<WorkDayDetailList, QWorkDayDetailList> workDayDetailLists = this.<WorkDayDetailList, QWorkDayDetailList>createList("workDayDetailLists", WorkDayDetailList.class, QWorkDayDetailList.class, PathInits.DIRECT2);

    public final NumberPath<Long> workDayId = createNumber("workDayId", Long.class);

    public final NumberPath<Integer> workDayState = createNumber("workDayState", Integer.class);

    public QWorkDay(String variable) {
        super(WorkDay.class, forVariable(variable));
    }

    public QWorkDay(Path<? extends WorkDay> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorkDay(PathMetadata metadata) {
        super(WorkDay.class, metadata);
    }

}

