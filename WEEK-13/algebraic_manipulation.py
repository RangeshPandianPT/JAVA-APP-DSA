from sympy import symbols, simplify, Eq

a, b = symbols('a b')

# Given expression
lhs = a**2 - a*b + a*b - b**2
rhs = a**2 - b**2

# Simplify both sides
simplified_lhs = simplify(lhs)
simplified_rhs = simplify(rhs)

# Check equality using symbolic equivalence
result = Eq(simplified_lhs, simplified_rhs)

# Variables to view results
simplified_lhs
simplified_rhs
result
