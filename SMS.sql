CREATE TABLE Customer
(
	CustomerId		int				PRIMARY KEY			IDENTITY(1,1),
	CustomerName	nvarchar(50)	NOT NULL
)

CREATE TABLE Employee
(
	EmployeeId		int 			PRIMARY KEY			IDENTITY(1,1),
	EmployeeName	nvarchar(50)	NOT NULL,
	Salary			money			NOT NULL,
	SupervisorId	int				FOREIGN KEY REFERENCES Employee(EmployeeId)
)

CREATE TABLE Product
(
	ProductId		int				PRIMARY KEY			IDENTITY(1,1),
	ProductName		nvarchar(50)	NOT NULL,
	ListPrice		money			NOT NULL
)

CREATE TABLE [Order]
(
	OrderId			int				PRIMARY KEY			IDENTITY(1,1),
	OrderDate		datetime		NOT NULL,
	CustomerId		int				FOREIGN KEY REFERENCES Customer(CustomerId),
	EmployeeId		int				FOREIGN KEY REFERENCES Employee(EmployeeId),
	Total			decimal(10,5)	NOT NULL
)

CREATE TABLE LineItem
(
	OrderId			int				FOREIGN KEY REFERENCES [Order](OrderId),
	ProductId		int				FOREIGN KEY REFERENCES Product(ProductId),
	Quantity		int				NOT NULL,
	Price			money			NOT NULL,
	PRIMARY KEY (OrderId, ProductId)
)


