package com.cpkj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QWorkTemp is a Querydsl query type for WorkTemp
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWorkTemp extends EntityPathBase<WorkTemp> {

    private static final long serialVersionUID = 618196795L;

    public static final QWorkTemp workTemp = new QWorkTemp("workTemp");

    public final DateTimePath<java.util.Date> onDay = createDateTime("onDay", java.util.Date.class);

    public final StringPath userId = createString("userId");

    public final StringPath workContent = createString("workContent");

    public final NumberPath<Double> workSuccess = createNumber("workSuccess", Double.class);

    public final NumberPath<Long> workTempDetailId = createNumber("workTempDetailId", Long.class);

    public QWorkTemp(String variable) {
        super(WorkTemp.class, forVariable(variable));
    }

    public QWorkTemp(Path<? extends WorkTemp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorkTemp(PathMetadata metadata) {
        super(WorkTemp.class, metadata);
    }

}

