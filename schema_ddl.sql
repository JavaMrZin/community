CREATE SCHEMA `community` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

/* user */
create table
    user
(
    id       bigint auto_increment comment 'ID',
    username varchar(255) not null comment '계정',
    password varchar(255) not null comment '패스워드',
    enabled  boolean      null comment '활성여부',
    constraint pk_user
        primary key (id),
    constraint uk_user_username
        unique (username)
)
    comment '회원';

/* role */
create table
    role
(
    id   bigint auto_increment comment 'ID',
    name varchar(255) not null comment '이름',
    constraint pk_role
        primary key (id)
)
    comment '역할';

/* user_role (회원_권한) */
create table
    user_role
(
    user_id bigint not null comment '회원 ID',
    role_id bigint not null comment '역할 ID',
    constraint pk_user_role
        primary key (user_id, role_id),
    constraint fk_user_role_role_id
        foreign key (role_id) references role (id),
    constraint fk_user_role_user_id
        foreign key (user_id) references user (id)
)
    comment '회원 권한';

/* post */
create table
    post
(
    id      bigint auto_increment comment 'ID',
    user_id bigint       not null comment '회원 ID',
    title   varchar(255) not null comment '제목',
    content text         null comment '내용',
    constraint pk_post
        primary key (id),
    constraint fk_post_user_id
        foreign key (user_id) references user (id)
)
    comment '게시글';




