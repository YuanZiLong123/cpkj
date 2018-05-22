package com.cpkj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWorkWeek is a Querydsl query type for WorkWeek
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWorkWeek extends EntityPathBase<WorkWeek> {

    private static final long serialVersionUID = 618285915L;

    public static final QWorkWeek workWeek = new QWorkWeek("workWeek");

    public final StringPath remark = createString("remark");

    public final DateTimePath<java.util.Date> subTime = createDateTime("subTime", java.util.Date.class);

    public final StringPath userId = createString("userId");

    public final NumberPath<Integer> weekNumber = createNumber("weekNumber", Integer.class);

    public final DateTimePath<java.util.Date> workweek = createDateTime("workweek", java.util.Date.class);

    public final ListPath<WorkWeekDetailList, QWorkWeekDetailList> workWeekDetailLists = this.<WorkWeekDetailList, QWorkWeekDetailList>createList("workWeekDetailLists", WorkWeekDetailList.class, QWorkWeekDetailList.class, PathInits.DIRECT2);

    public final NumberPath<Long> workweekId = createNumber("workweekId", Long.class);

    public final NumberPath<Integer> workWeekState = createNumber("workWeekState", Integer.class);

    public QWorkWeek(String variable) {
        super(WorkWeek.class, forVariable(variable));
    }

    public QWorkWeek(Path<? extends WorkWeek> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorkWeek(PathMetadata metadata) {
        super(WorkWeek.class, metadata);
    }

}

