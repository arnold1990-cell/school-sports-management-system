create table auth_users (
  id uuid primary key,
  email varchar(255) not null unique,
  password_hash varchar(255) not null,
  status varchar(32) not null,
  created_at timestamp not null default now()
);

create table auth_roles (
  id uuid primary key,
  name varchar(64) not null unique
);

create table auth_user_roles (
  user_id uuid not null references auth_users(id),
  role_id uuid not null references auth_roles(id),
  primary key (user_id, role_id)
);

create table auth_refresh_tokens (
  id uuid primary key,
  user_id uuid not null references auth_users(id),
  token varchar(512) not null,
  expires_at timestamp not null,
  revoked boolean not null default false
);

create table seasons (
  id uuid primary key,
  year int not null,
  term int not null,
  start_date date not null,
  end_date date not null,
  is_active boolean not null default false
);

create table houses (
  id uuid primary key,
  name varchar(100) not null,
  color varchar(50)
);

create table learners (
  id uuid primary key,
  admission_no varchar(50) not null unique,
  first_name varchar(100) not null,
  last_name varchar(100) not null,
  gender varchar(20) not null,
  dob date not null,
  grade varchar(20) not null,
  class_name varchar(20),
  guardian_phone varchar(50),
  notes text
);

create table house_assignments (
  learner_id uuid not null references learners(id),
  house_id uuid not null references houses(id),
  season_id uuid not null references seasons(id),
  assigned_at timestamp not null default now(),
  primary key (learner_id, season_id)
);

create table staff_profiles (
  id uuid primary key,
  user_id uuid not null references auth_users(id),
  first_name varchar(100) not null,
  last_name varchar(100) not null,
  phone varchar(50)
);

create table house_masters (
  house_id uuid not null references houses(id),
  staff_id uuid not null references staff_profiles(id),
  season_id uuid not null references seasons(id),
  primary key (house_id, season_id)
);

create table sports (
  id uuid primary key,
  name varchar(100) not null
);

create table age_groups (
  id uuid primary key,
  name varchar(50) not null,
  min_age int not null,
  max_age int not null
);

create table teams (
  id uuid primary key,
  name varchar(150) not null,
  sport_id uuid not null references sports(id),
  house_id uuid references houses(id),
  age_group_id uuid not null references age_groups(id),
  season_id uuid not null references seasons(id)
);

create table team_coaches (
  team_id uuid not null references teams(id),
  staff_id uuid not null references staff_profiles(id),
  primary key (team_id, staff_id)
);

create table team_members (
  team_id uuid not null references teams(id),
  learner_id uuid not null references learners(id),
  joined_at timestamp not null default now(),
  left_at timestamp,
  primary key (team_id, learner_id)
);

create table fixtures (
  id uuid primary key,
  team_id uuid not null references teams(id),
  opponent varchar(150) not null,
  venue varchar(150) not null,
  match_date_time timestamp not null,
  home_away varchar(20) not null,
  status varchar(30) not null
);

create table results (
  id uuid primary key,
  fixture_id uuid not null unique references fixtures(id),
  home_score int not null,
  away_score int not null,
  submitted_by uuid not null references staff_profiles(id),
  approved_by uuid references staff_profiles(id),
  approved_at timestamp,
  notes text
);

create table timetable_entries (
  id uuid primary key,
  season_id uuid not null references seasons(id),
  day_of_week varchar(20) not null,
  start_time time not null,
  end_time time not null,
  type varchar(30) not null,
  location varchar(150),
  team_id uuid references teams(id),
  club_id uuid
);

create table events (
  id uuid primary key,
  season_id uuid not null references seasons(id),
  title varchar(150) not null,
  description text,
  event_start timestamp not null,
  event_end timestamp not null,
  location varchar(150),
  created_by uuid not null references staff_profiles(id)
);

create table clubs (
  id uuid primary key,
  name varchar(150) not null,
  description text,
  type varchar(20) not null,
  fee_amount numeric(12,2),
  fee_frequency varchar(20),
  capacity int not null,
  season_id uuid not null references seasons(id),
  staff_id uuid not null references staff_profiles(id)
);

create table club_enrollments (
  id uuid primary key,
  club_id uuid not null references clubs(id),
  learner_id uuid not null references learners(id),
  status varchar(20) not null,
  enrolled_at timestamp not null default now(),
  unique (club_id, learner_id)
);

create table club_payments (
  id uuid primary key,
  enrollment_id uuid not null references club_enrollments(id),
  amount numeric(12,2) not null,
  paid_at timestamp not null,
  reference varchar(100)
);

create table audit_logs (
  id uuid primary key,
  actor_user_id uuid not null references auth_users(id),
  action varchar(100) not null,
  entity varchar(100) not null,
  entity_id varchar(100) not null,
  created_at timestamp not null default now(),
  details_json jsonb
);
