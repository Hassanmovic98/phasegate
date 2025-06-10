import datetime

days_in_month = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

cycle_history_file = "cycle_history.txt"


class CreateProfile:
    def __init__(self, name, age, blood_group, genotype):
        self.name = name
        self.age = age
        self.blood_group = blood_group
        self.genotype = genotype


   def display_profile(self):
    print("Profile:")
    print("Name:", self.name)
    print("Age:", self.age)
    print("Blood Group:", self.blood_group)
    print("Genotype:", self.genotype)

    present_time = datetime.datetime.now()
    print("Current time in Nigeria:", present_time.strftime("%H:%M:%S"))


def add_days(day, month, year, days_to_add):
    days_in_month[1] = 28  

    while days_to_add > 0:
        days_left = days_in_month[month - 1] - day
        if days_to_add <= days_left:
            day += days_to_add
            days_to_add = 0
        else:
            days_to_add -= (days_left + 1)
            day = 1
            month += 1
            if month > 12:
                month = 1
                year += 1

    return f"{year}-{str(month).zfill(2)}-{str(day).zfill(2)}"


def save_cycle_history(last_period, cycle_length, period_duration, next_start):
    try:
        with open(cycle_history_file, "a") as f:
            f.write(f"Last Period: {last_period}, Cycle Length: {cycle_length}, Period Duration: {period_duration}, Next Period Start: {next_start}")
    except:
        print("Error saving cycle history.")


def show_history():
    try:
        with open(cycle_history_file, "r") as f:
            lines = f.readlines()
            if not lines:
                print("No cycle history found yet.")
                return
            for line in lines:
                print("- " + line.strip())
    except:
        print("No cycle history found yet.")


def track_of_cycle():
    last_period = input("Enter the first day of your last menstrual period (yyyy-mm-dd): ")
    year, month, day = map(int, last_period.split("-"))

    period_duration = int(input("How many days of period do you experience on average?: "))
    cycle_length = int(input("What is your average cycle length in days?: "))

    next_start = add_days(day, month, year, cycle_length)

    next_end = add_days(day, month, year, cycle_length + period_duration - 1)

    ovulation_day = add_days(day, month, year, cycle_length - 14)

    fertile_start = add_days(day, month, year, cycle_length - 14 - 5)

    fertile_end = add_days(day, month, year, cycle_length - 14 + 1)

    print("Next Period Start:", next_start)

    print("Should End:", next_end)

    print("Ovulation Day:", ovulation_day)

    print("Fertile Days:", fertile_starts, "to", fertile_ends)

    print("Safe Sex Days: Before", fertile_start, "and After", fertile_end)

    save_cycle_history(last_period, cycle_length, period_duration, next_start)




print("Hello, You are Welcome to Semicolon’s Menstruation App")

print("We would love to know you, create a profile with us")

name = input("So, what is your name?: ")

age = int(input("Your age please: "))

blood_group = input("What is your blood group?: ")

genotype = input("What is your genotype?: ")

profile = CreateProfile(name, age, blood_group, genotype)

running = True
while running:

    print("Semicolon’s Menstruation Menu")

    print("1. View Profile")

    print("2. Track a New Cycle")

    print("3. View Cycle History")

    print("4. Exit")

    choice = input("Choose an option (1-4): ")

    if choice == "1":
        profile.display_profile()
    elif choice == "2":
        track_of_cycle()
    elif choice == "3":
        show_history()
    elif choice == "4":
        running = False
        print("Thank you for using Semicolon’s Menstrual App.")
    else:
        print("Invalid option. Only number 1-4 are valid options.")
