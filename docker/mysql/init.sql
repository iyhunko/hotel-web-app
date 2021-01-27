CREATE USER 'hotel_admin'@'%' IDENTIFIED BY 'test123';

CREATE DATABASE hotel_dev;
GRANT ALL PRIVILEGES ON hotel_dev.* TO 'hotel_admin'@'%';

CREATE DATABASE hotel_test;
GRANT ALL PRIVILEGES ON hotel_test.* TO 'hotel_admin'@'%';

FLUSH PRIVILEGES;
