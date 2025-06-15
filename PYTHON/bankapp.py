import random

accounts = {}

class Account:
    def create_account(self, name, phone_number):
        self.name = name
        self.phone_number = phone_number
        self.account_number = random.randint(1000000000, 9999999999)
        self.balance = 0.0
        print(f"Account created for {self.name}. Phone Number: {self.phone_number}. Account Number: {self.account_number}")

    def show_balance(self):
        print(f"{self.name}, your balance is ₦{self.balance:.2f}")

    def deposit(self, amount):
        if amount < 0:
            print("Invalid deposit amount.")
        elif amount > 1_000_000:
            print("Amount is too high for a new account, visit our nearest branch for upgrade")
        else:
            self.balance += amount
            print(f"₦{amount:.2f} deposited successfully.")

    def withdraw(self, amount):
        if amount < 0:
            print("Invalid withdrawal amount, enter a valid amount")
        elif amount > self.balance:
            print("Sorry, Insufficient funds")
        else:
            self.balance -= amount
            print(f"₦{amount:.2f} withdrawn successfully.")

def find_account_by_number(account_number):
    return accounts.get(account_number, None)

while True:
    print("Hassan Bank")
    print("1. Create Account")
    print("2. Show Balance")
    print("3. Deposit")
    print("4. Withdraw")
    print("5. Exit")

    choice = input("Choose your option (1-5): ")

    if choice == "1":
        name = input("Enter your name: ")
        phone_number = input("Enter your phone number: ")
        if len(phone_number) != 10 or not phone_number.isdigit():
            print("Invalid phone number.")
        else:
            new_account = Account()
            new_account.create_account(name, phone_number)
            accounts[new_account.account_number] = new_account

    elif choice == "2":
        acc_num = input("Enter your account number: ")
        if not acc_num.isdigit():
            print("Invalid account number, enter a valid account number")
        else:
            acc_num = int(acc_num)
            account = find_account_by_number(acc_num)
            if account:
                account.show_balance()
            else:
                print("Account not valid.")

    elif choice == "3":
        acc_num = input("Enter your account number: ")
        if not acc_num.isdigit():
            print("Invalid account number, enter a valid account number")
        else:
            acc_num = int(acc_num)
            account = find_account_by_number(acc_num)
            if account:
                amount_str = input("Enter deposit amount: ")
                try:
                    amount = float(amount_str)
                    account.deposit(amount)
                except ValueError:
                    print("Enter a valid number.")
            else:
                print("Account not found.")

    elif choice == "4":
        acc_num = input("Enter your account number: ")
        if not acc_num.isdigit():
            print("Invalid account number, enter a valid account number")
        else:
            acc_num = int(acc_num)
            account = find_account_by_number(acc_num)
            if account:
                amount_str = input("Enter withdrawal amount: ")
                try:
                    amount = float(amount_str)
                    account.withdraw(amount)
                except ValueError:
                    print("Enter a valid number.")
            else:
                print("Account not found.")

    elif choice == "5":
        print("Thank you for banking with us.")
        break

    else:
        print("Invalid choice")
