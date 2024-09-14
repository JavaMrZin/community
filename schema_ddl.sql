/* user */
create table
    user (
        id       bigint auto_increment comment 'ID',
        username varchar(255) not null comment '계정',
        password varchar(255) not null comment '패스워드',
        enabled  boolean      null comment '활성여부',
        constraint user_pk
            primary key (id),
        constraint user_username_uk
            unique (username)
    )
    comment '회원';

/* role */
create table
    role (
        id   bigint auto_increment comment 'ID',
        name varchar(255) not null comment '이름',
        constraint role_pk
            primary key (id)
    )
    comment '역할';

/* user_role (회원_권한) */
create table
    user_role (
        user_id bigint not null comment '회원 ID',
        role_id bigint not null comment '역할 ID',
        constraint user_role___fk_role_id
            foreign key (role_id) references role (id),
        constraint user_role___fk_user_id
            foreign key (user_id) references user (id)
    )
    comment '회원 권한';

/* post */
create table
    post (
        id      bigint auto_increment comment 'ID',
        title   varchar(255) not null comment '제목',
        content text         null comment '내용',
        constraint post_pk
            primary key (id)
    )
    comment '게시글';

alter table post
    add user_id bigint not null comment '회원 ID' after id;

alter table post
    add constraint post_user_id_fk
        foreign key (user_id) references user (id);



