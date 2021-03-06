function correctOnsets(style, name)
filename = strcat(style, '/', name);

onsets_file = fopen(strcat(filename, '.onsets'));
onsets = load(strcat(filename, '.onsets'));
fclose(onsets_file);

wav = wavread(strcat(filename, '.wav'));
onset_x = zeros(length(wav), 1);
onset_x(round(onsets)) = 1;

figure; hold on;
plot(wav, 'blue');
plot(onset_x, 'red');

X = onsets';
button = 1;
h = zoom;
set(h, 'Motion', 'Horizontal');
ax = gca;

while button ~= 'q'             % q to quit
    [x y button] = ginput(1);
    zoomfactor = 1;
    
    if(button ==1)
        plot([x x], [-1, 1], 'color', 'g');
        X = [X x];
    elseif(button == 'r')       % r to undo
        plot([X(:, end) X(:, end)], [-1, 1], 'color', 'w');
        X(:, end) = [];
    else
        if(button == 'z')       % z to zoom in
            zoomfactor = 1.5;
        elseif(button == 'x')   % x to zoom out
            zoomfactor = 0.5;
        end
        zoom(zoomfactor);
        cax = axis(ax);
        daxX = (cax(2)-cax(1))/zoomfactor/2;
        % daxY = (cax(4)-cax(3))/zoomfactor/2;
        daxY = (cax(4) - cax(3))/2;
        axis(ax,[x+[-1 1]*daxX y+[-1 1]*daxY]);
    end
    
end
output_file = fopen(strcat(filename, '.onsets'), 'w');
X = sort(X);
fprintf(output_file, '%d\n', X);
fclose(output_file);

