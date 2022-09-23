CREATE
OR REPLACE PROCEDURE checkingAndInsert( 
in firstName1 varchar(100),
in lastName1 varchar(100),
in email1 varchar(100),
in phoneNumber1 varchar(100),
in description1 varchar(250),
inout result_author BOOLEAN
)
LANGUAGE plpgsql
AS $$
BEGIN

if EXISTS (select firstName from author where firstName=firstName1
		 and  lastName=lastName1 and  email=email1 and phoneNumber=phoneNumber1
		 )
  then
begin
INSERT INTO author(
    is_deleted,  first_name, last_name, email, phone_number,description)
VALUES (false, firstName1, lastName1,email1 ,phoneNumber1, description1);
result_author=true;
end;
commit;
else
begin
   set result_author=false;
end;
end if;
END;$$