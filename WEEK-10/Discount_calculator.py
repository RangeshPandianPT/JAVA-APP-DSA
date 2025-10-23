def discount_calculator(rate):
    def apply_discount(price):
        discount_amount = price * rate
        final_price = price - discount_amount
        return final_price
    
    return apply_discount
print("Creating a 10% discount function...")
ten_percent = discount_calculator(0.1)

print("Creating a 50% discount function...")
fifty_percent = discount_calculator(0.5)

price = 2000
discounted_price_10 = ten_percent(price)

print("\n--- Applying 10% discount ---")
print(f"Original Price: {price}")
print(f"Final Price: {discounted_price_10}")

price_2 = 300
discounted_price_50 = fifty_percent(price_2)

print("\n--- Applying 50% discount ---")
print(f"Original Price: {price_2}")
print(f"Final Price: {discounted_price_50}")