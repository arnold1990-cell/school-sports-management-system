insert into auth_roles (id, name) values
  ('11111111-1111-1111-1111-111111111111', 'ADMIN'),
  ('22222222-2222-2222-2222-222222222222', 'TEACHER'),
  ('33333333-3333-3333-3333-333333333333', 'HOUSE_MASTER'),
  ('44444444-4444-4444-4444-444444444444', 'COACH'),
  ('55555555-5555-5555-5555-555555555555', 'STUDENT');

insert into auth_users (id, email, password_hash, status, created_at)
values ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'admin@school.local',
  '$2a$10$wM5PBogYvIY3KpBqjP3b7OaV8GqAekWdCzZrR1b6d9B2GZxW7g2Ee', 'ACTIVE', now());

insert into auth_user_roles (user_id, role_id) values
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '11111111-1111-1111-1111-111111111111');

insert into seasons (id, year, term, start_date, end_date, is_active)
values ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', extract(year from now())::int, 1, now()::date, (now()::date + interval '90 days')::date, true);

insert into houses (id, name, color) values
  ('c1111111-1111-1111-1111-111111111111', 'Flamingo', 'Pink'),
  ('c2222222-2222-2222-2222-222222222222', 'Fish Eagle', 'Blue'),
  ('c3333333-3333-3333-3333-333333333333', 'Hornbill', 'Orange'),
  ('c4444444-4444-4444-4444-444444444444', 'Ostrich', 'Green');

insert into age_groups (id, name, min_age, max_age) values
  ('d1111111-1111-1111-1111-111111111111', 'U11', 9, 11),
  ('d2222222-2222-2222-2222-222222222222', 'U13', 12, 13),
  ('d3333333-3333-3333-3333-333333333333', 'U15', 14, 15),
  ('d4444444-4444-4444-4444-444444444444', 'U17', 16, 17);

insert into sports (id, name) values
  ('e1111111-1111-1111-1111-111111111111', 'Football'),
  ('e2222222-2222-2222-2222-222222222222', 'Netball');
