-- Exercise 5: Return the total number of employees in a department
CREATE PROCEDURE sp_GetEmployeeCountByDepartment
    @DepartmentID INT
AS
BEGIN
    SELECT COUNT(*) AS EmployeeCount
    FROM Employees
    WHERE DepartmentID = @DepartmentID;
END;
GO

-- Test
EXEC sp_GetEmployeeCountByDepartment @DepartmentID = 3;
GO