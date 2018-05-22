package com.cpkj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWorkDayDetailList is a Querydsl query type for WorkDayDetailList
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWorkDayDetailList extends EntityPathBase<WorkDayDetailList> {

    private static final long serialVersionUID = -128290364L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWorkDayDetailList workDayDetailList = new QWorkDayDetailList("workDayDetailList");

    public final QWorkDay workDay;

    public final StringPath workDayContent = createString("workDayContent");

    public final NumberPath<Long> workDayDetailId = createNumber("workDayDetailId", Long.class);

    public final NumberPath<Double> workDaySuccess = createNumber("workDaySuccess", Double.class);

    public QWorkDayDetailList(String variable) {
        this(WorkDayDetailList.class, forVariable(variable), INITS);
    }

    public QWorkDayDetailList(Path<? extends WorkDayDetailList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWorkDayDetailList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWorkDayDetailList(PathMetadata metadata, PathInits inits) {
        this(WorkDayDetailList.class, metadata, inits);
    }

    public QWorkDayDetailList(Class<? extends WorkDayDetailList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.workDay = inits.isInitialized("workDay") ? new QWorkDay(forProperty("workDay")) : null;
    }

}

