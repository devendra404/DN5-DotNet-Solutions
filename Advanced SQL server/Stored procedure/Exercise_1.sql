IF OBJECT_ID('dbo.Employees', 'U') IS NOT NULL DROP TABLE dbo.Employees;
IF OBJECT_ID('dbo.Departments', 'U') IS NOT NULL DROP TABLE dbo.Departments;

CREATE TABLE Departments (
    DepartmentID   INT PRIMARY KEY,
    DepartmentName VARCHAR(100) NOT NULL
);

CREATE TABLE Employees (
    EmployeeID   INT PRIMARY KEY,
    FirstName    VARCHAR(50),
    LastName     VARCHAR(50),
    DepartmentID INT,
    Salary       DECIMAL(10,2),
    JoinDate     DATE,
    FOREIGN KEY (DepartmentID) REFERENCES Departments(DepartmentID)
);

INSERT INTO Departments (DepartmentID, DepartmentName) VALUES
(1, 'HR'),
(2, 'Finance'),
(3, 'IT'),
(4, 'Marketing');

INSERT INTO Employees (EmployeeID, FirstName, LastName, DepartmentID, Salary, JoinDate) VALUES
(1, 'John',    'Doe',     1, 5000.00, '2020-01-15'),
(2, 'Jane',    'Smith',   2, 6000.00, '2019-03-22'),
(3, 'Michael', 'Johnson', 3, 7000.00, '2018-07-30'),
(4, 'Emily',   'Davis',   4, 5500.00, '2021-11-05');
GO

-- Exercise 1a: Retrieve employee details by DepartmentID
CREATE PROCEDURE sp_GetEmployeesByDepartment
    @DepartmentID INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        e.EmployeeID,
        e.FirstName,
        e.LastName,
        d.DepartmentName,
        e.Salary,
        e.JoinDate
    FROM Employees e
    JOIN Departments d ON e.DepartmentID = d.DepartmentID
    WHERE e.DepartmentID = @DepartmentID;
END;
GO

-- EXEC sp_GetEmployeesByDepartment @DepartmentID = 3;
GO

-- Exercise 1b: Insert a new employee record
CREATE PROCEDURE sp_InsertEmployee
    @FirstName    VARCHAR(50),
    @LastName     VARCHAR(50),
    @DepartmentID INT,
    @Salary       DECIMAL(10,2),
    @JoinDate     DATE
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @NewEmployeeID INT;
    SELECT @NewEmployeeID = ISNULL(MAX(EmployeeID), 0) + 1 FROM Employees;

    INSERT INTO Employees (EmployeeID, FirstName, LastName, DepartmentID, Salary, JoinDate)
    VALUES (@NewEmployeeID, @FirstName, @LastName, @DepartmentID, @Salary, @JoinDate);
END;
GO

-- EXEC sp_InsertEmployee @FirstName = 'Sara', @LastName = 'Lee', @DepartmentID = 3, @Salary = 6200.00, @JoinDate = '2022-05-01';
-- EXEC sp_GetEmployeesByDepartment @DepartmentID = 3;
GO