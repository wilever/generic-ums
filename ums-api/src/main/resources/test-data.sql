-- Insert data to any_entity_type
insert into any_entity_type(any_type_id,type_name,active_ind) 
values ('0','EntityEven','Y') on conflict(any_type_id) do nothing;
insert into any_entity_type(any_type_id,type_name,active_ind) 
values ('1','EntityOdd','Y') on conflict(any_type_id) do nothing;
-- Insert data to any_entity
insert into any_entity(any_id,field,active_ind,any_type_id) 
values ('0','field','Y','0') on conflict(any_id) do nothing;
insert into any_entity(any_id,field,active_ind,any_type_id) 
values ('1','field','N','1') on conflict(any_id) do nothing;
insert into any_entity(any_id,field,active_ind,any_type_id) 
values ('2','field','Y','0') on conflict(any_id) do nothing;
insert into any_entity(any_id,field,active_ind,any_type_id) 
values ('3','anotherField','N','1') on conflict(any_id) do nothing;
insert into any_entity(any_id,field,active_ind,any_type_id) 
values ('4','anotherField','Y','0') on conflict(any_id) do nothing;
insert into any_entity(any_id,field,active_ind,any_type_id) 
values ('5','anotherField',null,'1') on conflict(any_id) do nothing;
-- Insert data to any_entity_detail
insert into any_entity_detail(any_id,any_entity_detail_id,detail_field,active_ind)
values ('0','0','field','Y') on conflict(any_id,any_entity_detail_id) do nothing;
insert into any_entity_detail(any_id,any_entity_detail_id,detail_field,active_ind)
values ('1','0','field','N') on conflict(any_id,any_entity_detail_id) do nothing;
insert into any_entity_detail(any_id,any_entity_detail_id,detail_field,active_ind)
values ('2','0','field','Y') on conflict(any_id,any_entity_detail_id) do nothing;
insert into any_entity_detail(any_id,any_entity_detail_id,detail_field,active_ind)
values ('3','0','anotherField','N') on conflict(any_id,any_entity_detail_id) do nothing;
insert into any_entity_detail(any_id,any_entity_detail_id,detail_field,active_ind)
values ('4','0','anotherField','Y') on conflict(any_id,any_entity_detail_id) do nothing;
insert into any_entity_detail(any_id,any_entity_detail_id,detail_field,active_ind)
values ('5','0','anotherField',null) on conflict(any_id,any_entity_detail_id) do nothing;
-- Insert data to rule
insert into rule(r_id,r_code,r_message,r_table,r_column,r_condition,r_expected,r_operator) 
values ('0','RuleCode','Data can not be UNACTIVE', 'any_entity','active_ind','UPDATE', 'Y','EQUAL')
on conflict(r_id) do nothing;
insert into rule(r_id,r_code,r_message,r_table,r_column,r_condition,r_expected,r_operator) 
values ('1','RuleCode','Data can not be UNACTIVE', 'any_entity','active_ind','DELETE', 'Y','EQUAL')
on conflict(r_id) do nothing;
insert into rule(r_id,r_code,r_message,r_table,r_column,r_condition,r_expected,r_operator) 
values ('2','RuleCode','Data can not be ACTIVE', 'any_entity','active_ind','RESTORE', 'N','EQUAL')
on conflict(r_id) do nothing;
