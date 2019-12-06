% Compile and Run:
% erl -compile alicebob && erl -noshell -s alicebob start -s init stop

-module(alicebob).
-export([start/0, boss/4, employee/0]).


boss(0, Counter, Sum, Employees) ->
	receive
		{res, R}-> io:format("Alice has:~p\n", [Sum+R])
	end,
	if
		Sum+R < 100 ->
			Index = (Counter rem length(Employees)) + 1,
			Target = lists:nth(Index, Employees),
			Target ! {req, Counter+1, self()},
			boss(0, Counter+1, Sum+R, Employees);
		true ->
			io:format("Alice is Done\n")
	end;
boss(N, Counter, Sum, Employees) ->
	E = spawn(alicebob, employee, []),
	E ! {req, Counter, self()},
	boss(N-1, Counter+1, Sum, [E] ++ Employees).
	
employee()->
	receive
		{req, N, Pid}-> io:format("Employee Received:~p\n", [N]),
			Pid ! {res, 2*N}
	end,
	employee().
	
	
start()->
	boss(5, 0, 0, []).