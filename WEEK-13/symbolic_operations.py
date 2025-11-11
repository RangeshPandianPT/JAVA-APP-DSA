from sympy import symbols, integrate, sin, diff, Rational

# Define symbols
a, x, y = symbols('a x y')

# a. ∬ a² da  (Indefinite integration)
expr_a = integrate(a**2, a)

# b. 2x + y²
expr_b = 2*x + y**2

# c. 1/10 + 1/5  (Rational arithmetic)
expr_c = Rational(1, 10) + Rational(1, 5)

# d. d/dx (sin(x))
expr_d = diff(sin(x), x)

# Variables to hold results
expr_a
expr_b
expr_c
expr_d
