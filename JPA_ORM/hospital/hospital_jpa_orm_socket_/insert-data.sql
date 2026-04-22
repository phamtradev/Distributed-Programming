INSERT INTO People (personId, fullName, email)
VALUES ('D01', 'James Wilson', 'j.wilson@hospital.com'),
       ('D02', 'Emily Blunt', 'emily.b@clinic.org'),
       ('D03', 'Robert Chen', 'rchen.cardio@med.com'),
       ('P01', 'John Doe', 'johndoe@gmail.com'),
       ('P02', 'Alice Freeman', 'alice.f@outlook.com'),
       ('P03', 'Kevin Hart', 'kevin.h@yahoo.com');

INSERT INTO Doctors (doctorId, specialty, hospital)
VALUES ('D01', 'Cardiology', 'General City Hospital'),
       ('D02', 'Dermatology', 'Skin Care Institute'),
       ('D03', 'Cardiology', 'St. Luke Heart Center');

INSERT INTO Patients (patientId, address)
VALUES ('P01', '123 Maple Street, NY'),
       ('P02', '456 Oak Avenue, CA'),
       ('P03', '789 Pine Road, TX');

INSERT INTO Phones (patientId, phoneNumber)
VALUES ('P01', '+1-202-555-0101'),
       ('P01', '+1-202-555-0199'),
       ('P02', '+1-305-555-0123'),
       ('P03', '+1-415-555-0144');

INSERT INTO Appointments (doctorId, patientId, appointmentTime, status)
VALUES ('D01', 'P01', '2026-05-10 08:00:00', 'CONFIRMED'),
       ('D01', 'P02', '2026-05-10 14:30:00', 'CONFIRMED'),
       ('D03', 'P01', '2026-05-11 09:00:00', 'PENDING'),
       ('D02', 'P03', '2026-05-12 10:00:00', 'CANCELLED'),
       ('D01', 'P03', '2026-05-13 11:15:00', 'CONFIRMED');