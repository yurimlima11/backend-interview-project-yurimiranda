INSERT INTO device_type(id, device_type_name) values (1, 'Windows Workstation'); 
INSERT INTO device_type(id, device_type_name) values (2, 'Windows Server');
INSERT INTO device_type(id, device_type_name) values (3, 'Mac');
INSERT INTO device_type(id, device_type_name) values (4, 'Linux');


INSERT INTO device(id, system_name, device_type) values (1, 'System-Yuri', 1);
INSERT INTO device(id, system_name, device_type) values (2, 'System-Yuri', 3);
INSERT INTO device(id, system_name, device_type) values (3, 'System-Yuri', 4);

INSERT INTO device(id, system_name, device_type) values (4, 'System-NinjaOne', 4);
INSERT INTO device(id, system_name, device_type) values (5, 'System-NinjaOne', 2);

INSERT INTO services_type(id, service_name, costs) values (1, 'Antivirus', 5.0);
INSERT INTO services_type(id, service_name, costs) values (2, 'Backup', 3.0);
INSERT INTO services_type(id, service_name, costs) values (3, 'PSA', 2.0);
INSERT INTO services_type(id, service_name, costs) values (4, 'Screen Share', 1.0);

INSERT INTO services(id, device, services_type) values (1, 1, 1);
INSERT INTO services(id, device, services_type) values (2, 3, 4);
INSERT INTO services(id, device, services_type) values (3, 2, 2);
INSERT INTO services(id, device, services_type) values (4, 2, 1);


INSERT INTO services(id, device, services_type) values (5, 5, 1);
INSERT INTO services(id, device, services_type) values (6, 4, 3);