PGDMP     
         
        	    x            uml_teacher_db    12.4    12.4 *    O           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            P           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            Q           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            R           1262    16404    uml_teacher_db    DATABASE     �   CREATE DATABASE uml_teacher_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE uml_teacher_db;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                postgres    false            S           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   postgres    false    3            �            1259    16445    course    TABLE     o   CREATE TABLE public.course (
    cource_id integer NOT NULL,
    course_name character varying(40) NOT NULL
);
    DROP TABLE public.course;
       public         heap    postgres    false    3            �            1259    16469    course_edumat    TABLE     �   CREATE TABLE public.course_edumat (
    course_id integer NOT NULL,
    edumat_id integer NOT NULL,
    task_number bytea NOT NULL
);
 !   DROP TABLE public.course_edumat;
       public         heap    postgres    false    3            �            1259    16463    course_task    TABLE     �   CREATE TABLE public.course_task (
    course_id integer NOT NULL,
    task_id integer NOT NULL,
    task_number bytea NOT NULL
);
    DROP TABLE public.course_task;
       public         heap    postgres    false    3            �            1259    16435    edu    TABLE     g   CREATE TABLE public.edu (
    edu_id smallint NOT NULL,
    edu_name character varying(60) NOT NULL
);
    DROP TABLE public.edu;
       public         heap    postgres    false    3            �            1259    16455    edumat    TABLE     p   CREATE TABLE public.edumat (
    edumat_id integer NOT NULL,
    edumat_path character varying(255) NOT NULL
);
    DROP TABLE public.edumat;
       public         heap    postgres    false    3            �            1259    16425    employee    TABLE     �   CREATE TABLE public.employee (
    employee_id integer NOT NULL,
    employee_hiring_date date NOT NULL,
    employee_phone_number character varying(20) NOT NULL,
    employee_email character varying(20) NOT NULL,
    user_id integer NOT NULL
);
    DROP TABLE public.employee;
       public         heap    postgres    false    3            T           0    0    TABLE employee    COMMENT     P   COMMENT ON TABLE public.employee IS 'Used instead the "stuff" entity in docs.';
          public          postgres    false    204            �            1259    16440    group    TABLE     �   CREATE TABLE public."group" (
    group_id integer NOT NULL,
    group_name character varying(30) NOT NULL,
    current_cource_id integer NOT NULL,
    first_teacher_id integer NOT NULL,
    second_teacher_id integer
);
    DROP TABLE public."group";
       public         heap    postgres    false    3            �            1259    16415    role    TABLE     j   CREATE TABLE public.role (
    role_name character varying(30) NOT NULL,
    role_id smallint NOT NULL
);
    DROP TABLE public.role;
       public         heap    postgres    false    3            �            1259    16430    student    TABLE     �   CREATE TABLE public.student (
    student_id integer NOT NULL,
    edu_id smallint NOT NULL,
    user_contacts character varying(100) NOT NULL,
    user_id integer NOT NULL
);
    DROP TABLE public.student;
       public         heap    postgres    false    3            �            1259    16460    student_group    TABLE     c   CREATE TABLE public.student_group (
    group_id integer NOT NULL,
    user_id integer NOT NULL
);
 !   DROP TABLE public.student_group;
       public         heap    postgres    false    3            �            1259    16450    task    TABLE     j   CREATE TABLE public.task (
    task_id integer NOT NULL,
    task_path character varying(255) NOT NULL
);
    DROP TABLE public.task;
       public         heap    postgres    false    3            �            1259    16405    users    TABLE     �   CREATE TABLE public.users (
    user_id integer NOT NULL,
    user_username character varying(30) NOT NULL,
    user_password character varying(80) NOT NULL,
    user_fullname character varying(80) NOT NULL,
    role_id smallint NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false    3            G          0    16445    course 
   TABLE DATA           8   COPY public.course (cource_id, course_name) FROM stdin;
    public          postgres    false    208   +       L          0    16469    course_edumat 
   TABLE DATA           J   COPY public.course_edumat (course_id, edumat_id, task_number) FROM stdin;
    public          postgres    false    213   5+       K          0    16463    course_task 
   TABLE DATA           F   COPY public.course_task (course_id, task_id, task_number) FROM stdin;
    public          postgres    false    212   R+       E          0    16435    edu 
   TABLE DATA           /   COPY public.edu (edu_id, edu_name) FROM stdin;
    public          postgres    false    206   o+       I          0    16455    edumat 
   TABLE DATA           8   COPY public.edumat (edumat_id, edumat_path) FROM stdin;
    public          postgres    false    210   �+       C          0    16425    employee 
   TABLE DATA           u   COPY public.employee (employee_id, employee_hiring_date, employee_phone_number, employee_email, user_id) FROM stdin;
    public          postgres    false    204   �+       F          0    16440    group 
   TABLE DATA           o   COPY public."group" (group_id, group_name, current_cource_id, first_teacher_id, second_teacher_id) FROM stdin;
    public          postgres    false    207   �+       B          0    16415    role 
   TABLE DATA           2   COPY public.role (role_name, role_id) FROM stdin;
    public          postgres    false    203   �+       D          0    16430    student 
   TABLE DATA           M   COPY public.student (student_id, edu_id, user_contacts, user_id) FROM stdin;
    public          postgres    false    205   ,       J          0    16460    student_group 
   TABLE DATA           :   COPY public.student_group (group_id, user_id) FROM stdin;
    public          postgres    false    211   /,       H          0    16450    task 
   TABLE DATA           2   COPY public.task (task_id, task_path) FROM stdin;
    public          postgres    false    209   L,       A          0    16405    users 
   TABLE DATA           ^   COPY public.users (user_id, user_username, user_password, user_fullname, role_id) FROM stdin;
    public          postgres    false    202   i,       �
           2606    16414    users account_username 
   CONSTRAINT     Z   ALTER TABLE ONLY public.users
    ADD CONSTRAINT account_username UNIQUE (user_username);
 @   ALTER TABLE ONLY public.users DROP CONSTRAINT account_username;
       public            postgres    false    202            �
           2606    16412    users cACCOUNT_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.users
    ADD CONSTRAINT "cACCOUNT_pkey" PRIMARY KEY (user_id);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT "cACCOUNT_pkey";
       public            postgres    false    202            �
           2606    16449    course cource_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.course
    ADD CONSTRAINT cource_pkey PRIMARY KEY (cource_id);
 <   ALTER TABLE ONLY public.course DROP CONSTRAINT cource_pkey;
       public            postgres    false    208            �
           2606    16439    edu edu_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.edu
    ADD CONSTRAINT edu_pkey PRIMARY KEY (edu_id);
 6   ALTER TABLE ONLY public.edu DROP CONSTRAINT edu_pkey;
       public            postgres    false    206            �
           2606    16459    edumat edumat_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.edumat
    ADD CONSTRAINT edumat_pkey PRIMARY KEY (edumat_id);
 <   ALTER TABLE ONLY public.edumat DROP CONSTRAINT edumat_pkey;
       public            postgres    false    210            �
           2606    16429    employee employee_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (employee_id);
 @   ALTER TABLE ONLY public.employee DROP CONSTRAINT employee_pkey;
       public            postgres    false    204            �
           2606    16444    group group_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public."group"
    ADD CONSTRAINT group_pkey PRIMARY KEY (group_id);
 <   ALTER TABLE ONLY public."group" DROP CONSTRAINT group_pkey;
       public            postgres    false    207            �
           2606    16476    role role_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (role_id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public            postgres    false    203            �
           2606    16424    role role_role_name_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_role_name_key UNIQUE (role_name);
 A   ALTER TABLE ONLY public.role DROP CONSTRAINT role_role_name_key;
       public            postgres    false    203            �
           2606    16454    task task_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_pkey PRIMARY KEY (task_id);
 8   ALTER TABLE ONLY public.task DROP CONSTRAINT task_pkey;
       public            postgres    false    209            �
           2606    16434    student user_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.student
    ADD CONSTRAINT user_pkey PRIMARY KEY (student_id);
 ;   ALTER TABLE ONLY public.student DROP CONSTRAINT user_pkey;
       public            postgres    false    205            G      x������ � �      L      x������ � �      K      x������ � �      E      x������ � �      I      x������ � �      C      x������ � �      F      x������ � �      B      x���4�
��q�v�4����� C:�      D      x������ � �      J      x������ � �      H      x������ � �      A   f   x�3�L�(�6�2�t�wr��T1JT14P�
��J3̏2�p5��s�p,uOL-u�
7ps�ψ�*M�KI.O/�r�5.v�N�����IL���,�4����� B�=�     