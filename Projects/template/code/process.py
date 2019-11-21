import pandas as pd
import seaborn as sns

sns.set(style="whitegrid")

df = pd.read_csv("output.csv", sep=";")
df.columns = ["Version", "Time (s)"]
df['Time (s)'] = df['Time (s)'] / (1000*1000*1000)
df['Version'] = df['Version'].map(lambda x: x and "Sequential" or "Parallel")
df = df.sort_values(by='Version', ascending=False)

ax = sns.violinplot(x="Version", y="Time (s)", data=df)
fig = ax.get_figure()
fig.savefig('performance.eps')