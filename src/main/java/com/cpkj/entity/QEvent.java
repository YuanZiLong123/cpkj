package com.cpkj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEvent is a Querydsl query type for Event
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEvent extends EntityPathBase<Event> {

    private static final long serialVersionUID = -306332252L;

    public static final QEvent event = new QEvent("event");

    public final StringPath createUserId = createString("createUserId");

    public final NumberPath<Double> dayNumber = createNumber("dayNumber", Double.class);

    public final StringPath dealContent = createString("dealContent");

    public final StringPath eventContent = createString("eventContent");

    public final DateTimePath<java.util.Date> eventCreateDate = createDateTime("eventCreateDate", java.util.Date.class);

    public final NumberPath<Long> eventId = createNumber("eventId", Long.class);

    public final NumberPath<Integer> eventState = createNumber("eventState", Integer.class);

    public final NumberPath<Integer> eventType = createNumber("eventType", Integer.class);

    public QEvent(String variable) {
        super(Event.class, forVariable(variable));
    }

    public QEvent(Path<? extends Event> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEvent(PathMetadata metadata) {
        super(Event.class, metadata);
    }

}

