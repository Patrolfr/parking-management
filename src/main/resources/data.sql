-- -- --Bills
-- insert into parking_bill(id, PARKING_FEE, PARKING_TIME_IN_HOURS) values (1, 2.2, 5);
insert into parking_bill values (1, '2018-12-28', 'REGULAR', 'WEQ2123', 2.22, 'FINISHED', 1 );
insert into parking_bill values (2, '2018-12-28', 'REGULAR', 'XFA2124', 3.33, 'FINISHED', 2 );
insert into parking_bill values (3, '2018-12-28', 'REGULAR', 'ASD2123', 4.44, 'FINISHED', 3 );
insert into parking_bill values (4, '2018-12-28', 'REGULAR', 'MGQ2124', 5.55, 'FINISHED', 6 );
--
insert into parking_bill values (5, '2018-12-29', 'REGULAR', 'ASD2123', 4.44, 'FINISHED', 5 );
insert into parking_bill values (6, '2018-12-29', 'REGULAR', 'MGQ2124', 5.55, 'FINISHED', 6 );
--
insert into parking_bill values (7, null, 'REGULAR', 'ASD2123', 0, 'OCCUPIED', 0);
insert into parking_bill values (8, null, 'DISABLED', 'MGQ2124', 0, 'OCCUPIED', 0);
insert into parking_bill values (9, null, 'DISABLED', 'LLW2124', 0, 'OCCUPIED', 0);

--finished parkings
--  12-28
insert into parking_meter values (1, 'KJS-417', '2018-12-28 13:20:00', '2018-12-28 15:46:06', 1);
insert into parking_meter values (2, 'ZXC-417', '2018-12-28 13:20:00', '2018-12-28 16:46:06', 2);
insert into parking_meter values (3, 'KJS-417', '2018-12-28 13:20:00', '2018-12-28 17:46:06', 3);
insert into parking_meter values (4, 'KJS-417', '2018-12-28 13:20:00', '2018-12-28 18:46:06', 4);
-- -- 12-29
insert into parking_meter values (5, 'KJS-417', '2018-12-29 13:20:00', '2018-12-29 17:46:06', 5);
insert into parking_meter values (6, 'KJS-417', '2018-12-29 13:20:00', '2018-12-29 18:46:06', 6);
-- -- -- --Occupied parkings
insert into parking_meter values (7, 'TST-123', '2018-12-30 13:20:00', null, 7);
insert into parking_meter values (8, 'STS-123', '2018-12-30 13:20:00', null, 8);
insert into parking_meter values (9, 'STS-123', '2018-12-30 13:20:00', null, 9);



