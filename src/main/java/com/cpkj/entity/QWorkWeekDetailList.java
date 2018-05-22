package com.cpkj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWorkWeekDetailList is a Querydsl query type for WorkWeekDetailList
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWorkWeekDetailList extends EntityPathBase<WorkWeekDetailList> {

    private static final long serialVersionUID = -2096259510L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWorkWeekDetailList workWeekDetailList = new QWorkWeekDetailList("workWeekDetailList");

    public final QWorkWeek workWeek;

    public final StringPath workweekContent = createString("workweekContent");

    public final NumberPath<Long> workweekDetailId = createNumber("workweekDetailId", Long.class);

    public final NumberPath<Double> workweekSuccess = createNumber("workweekSuccess", Double.class);

    public QWorkWeekDetailList(String variable) {
        this(WorkWeekDetailList.class, forVariable(variable), INITS);
    }

    public QWorkWeekDetailList(Path<? extends WorkWeekDetailList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWorkWeekDetailList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWorkWeekDetailList(PathMetadata metadata, PathInits inits) {
        this(WorkWeekDetailList.class, metadata, inits);
    }

    public QWorkWeekDetailList(Class<? extends WorkWeekDetailList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.workWeek = inits.isInitialized("workWeek") ? new QWorkWeek(forProperty("workWeek")) : null;
    }

}

