from sympy import symbols, Eq, solveset, S, sqrt, Rational, expand, simplify, sin, cos, N

x, y, n = symbols('x y n')

# 1. Equations
expr1 = x + x * y / x
simplified_expr1 = simplify(expr1)
solution2 = solveset(x**4 - 1, x)

# 2. Symbolic calculations
sqrt_2_precise = N(sqrt(2), 100)
rational_sum = Rational(1, 2) + Rational(1, 3)
expanded_form = expand((x + y) ** 6)
trig_simplified = simplify(sin(x) / cos(x))
expr5 = simplify(sin(x) - x * x**3 * n)

# Results (variables)
simplified_expr1
solution2
sqrt_2_precise
rational_sum
expanded_form
trig_simplified
expr5
