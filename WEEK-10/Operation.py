def add(a, b):
    return a + b

def subtract(a, b):
    return a - b

def multiply(a, b):
    return a * b

def divide(a, b):
    if b == 0:
        return "Error: Cannot divide by zero"
    return a / b

def operate(func, a, b):
    return func(a, b)

if __name__ == "__main__":
    num1, num2 = 100, 25

    print(f"Operating with numbers: {num1} and {num2}")
    print("-" * 35)

    result_add = operate(add, num1, num2)
    print(f"Result of add: {result_add}")

    result_sub = operate(subtract, num1, num2)
    print(f"Result of subtract: {result_sub}")

    result_mul = operate(multiply, num1, num2)
    print(f"Result of multiply: {result_mul}")

    result_div = operate(divide, num1, num2)
    print(f"Result of divide: {result_div}")

    def power(base, exponent):
        return base ** exponent

    print("\n--- Testing new 'power' function ---")
    result_pow = operate(power, 2, 8)
    print(f"Result of power(2, 8): {result_pow}")
