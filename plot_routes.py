import matplotlib.pyplot as plt
import csv


def get_axis_from_csv(csv_file):
    x = []
    y = []

    with open(csv_file) as initial_route_file:
        reader = csv.reader(initial_route_file)

        skip = True
        for row in reader:
            if skip:
                skip = False
            else:
                x.append(int(row[0]))
                y.append(int(row[1]))

    return x, y


def plot_graph(x_axis, y_axis, title, fig_num):

    plt.figure(fig_num)
    plt.plot(x_axis, y_axis, marker='o')
    plt.title(title)
    plt.xlabel("x coordinate")
    plt.ylabel("y coordinate")


def main():
    initial_x, initial_y = get_axis_from_csv("InitialRoute.csv")
    plot_graph(initial_x, initial_y, "Initial Route", 1)

    final_x, final_y = get_axis_from_csv("FinalRoute.csv")
    plot_graph(final_x, final_y, "Final Route", 2)

    plt.show()

if __name__ == "__main__":
    main()
