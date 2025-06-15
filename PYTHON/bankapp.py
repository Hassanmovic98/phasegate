import random
import datetime

accounts = {}

class Account:
    def __init__(self, name, phone_number):
        self.name = name
        self.phone_number = phone_number
        self.account_number = random.randint(1000000000, 9999999999)
        self.balance = 0.0
        self.history = []

    def show_balance(self):
        print(f"{self.name}, your balance is ₦{self.balance:.2f}")

    def deposit(self, amount):
        if amount < 0:
            print("Invalid deposit amount.")
        elif amount > 1_000_000:
            print("Amount is too high for a new account, visit our nearest branch for upgrade")
        else:
            self.balance += amount
            self.history.append(f"{datetime.datetime.now()}: Deposited ₦{amount:.2f}")
            print(f"₦{amount:.2f} deposited successfully.")

    def withdraw(self, amount):
        if amount < 0:
            print("Invalid withdrawal amount, enter a valid amount")
        elif amount > self.balance:
            print("Sorry, Insufficient funds")
        else:
            self.balance -= amount
            self.history.append(f"{datetime.datetime.now()}: Withdrew ₦{amount:.2f}")
            print(f"₦{amount:.2f} withdrawn successfully.")

    def transfer(self, amount, recipient_account):
        if amount <= 0:
            print("Enter a valid transfer amount.")
        elif amount > self.balance:
            print("Insufficient funds for transfer.")
        else:
            self.balance -= amount
            recipient_account.balance += amount
            now = datetime.datetime.now()
            self.history.append(f"{now}: Transferred ₦{amount:.2f} to {recipient_account.name}")
            recipient_account.history.append(f"{now}: Received ₦{amount:.2f} from {self.name}")
            print(f"₦{amount:.2f} transferred successfully to {recipient_account.name} ({recipient_account.account_number}).")

def find_account_by_number(account_number):
    return accounts.get(account_number, None)

while True:
    print("Hassan Bank")
    print("1. Create Account")
    print("2. Show Balance")
    print("3. Deposit")
    print("4. Withdraw")
    print("5. Transfer")
    print("6. Show Transaction History")
    print("7. Exit")

    choice = input("Choose your option (1-7): ")

    if choice == "1":
        name = input("Enter your name: ")
        phone_number = input("Enter your phone number: ")
        if len(phone_number) != 10 or not phone_number.isdigit():
            print("Invalid phone number.")
        else:
            new_account = Account(name, phone_number)
            accounts[new_account.account_number] = new_account
            print(f"Account created for {new_account.name}. Phone Number: {new_account.phone_number}. Account Number: {new_account.account_number}")

    elif choice == "2":
        acc_num = input("Enter your account number: ")
        if acc_num.isdigit():
            acc_num = int(acc_num)
            account = find_account_by_number(acc_num)
            if account:
                account.show_balance()
            else:
                print("Account not found.")
        else:
            print("Invalid account number.")

    elif choice == "3":
        acc_num = input("Enter your account number: ")
        if acc_num.isdigit():
            acc_num = int(acc_num)
            account = find_account_by_number(acc_num)
            if account:
                try:
                    amount = float(input("Enter deposit amount: "))
                    account.deposit(amount)
                except ValueError:
                    print("Enter a valid amount.")
            else:
                print("Account not found.")
        else:
            print("Invalid account number.")

    elif choice == "4":
        acc_num = input("Enter your account number: ")
        if acc_num.isdigit():
            acc_num = int(acc_num)
            account = find_account_by_number(acc_num)
            if account:
                try:
                    amount = float(input("Enter withdrawal amount: "))
                    account.withdraw(amount)
                except ValueError:
                    print("Enter a valid amount.")
            else:
                print("Account not found.")
        else:
            print("Invalid account number.")

    elif choice == "5":
        sender_acc = input("Enter your account number: ")
        receiver_acc = input("Enter recipient's account number: ")

        if sender_acc.isdigit() and receiver_acc.isdigit():
            sender = find_account_by_number(int(sender_acc))
            receiver = find_account_by_number(int(receiver_acc))

            if sender and receiver:
                try:
                    amount = float(input("Enter amount to transfer: "))
                    sender.transfer(amount, receiver)
                except ValueError:
                    print("Enter a valid amount.")
            else:
                print("Sender or recipient account not found.")
        else:
            print("Invalid account number(s).")

    elif choice == "6":
        acc_num = input("Enter your account number: ")
        if acc_num.isdigit():
            acc_num = int(acc_num)
            account = find_account_by_number(acc_num)
            if account:
                print(f"Transaction history for {account.name}:")
                for item in account.history:
                    print(" -", item)
            else:
                print("Account not found.")
        else:
            print("Invalid account number.")

    elif choice == "7":
        print("Thank you for banking with us.")
        break

    else:
        print("Invalid choice.")









