def festival_bonus(salary):
    bonus = 5000.0
    print(f"  Adding fixed festival bonus: ${bonus}")
    return salary + bonus

def performance_bonus(salary):
    bonus = salary * 0.10
    print(f"  Adding 10% performance bonus: ${bonus}")
    return salary + bonus

def long_service_bonus(salary):
    bonus = salary * 0.05
    print(f"  Adding 5% long service bonus: ${bonus}")
    return salary + bonus

def apply_bonus(bonus_function, salary):
    print(f"\nCalculating with {bonus_function.__name__}...")
    return bonus_function(salary)

if __name__ == "__main__":
    base_salary = 50000.0
    print(f"Base Salary: ${base_salary}")
    print("-" * 30)

    final_salary_perf = apply_bonus(performance_bonus, base_salary)
    print(f" -> Final Salary: ${final_salary_perf}")

    final_salary_fest = apply_bonus(festival_bonus, base_salary)
    print(f" -> Final Salary: ${final_salary_fest}")

    final_salary_service = apply_bonus(long_service_bonus, base_salary)
    print(f" -> Final Salary: ${final_salary_service}")

